package com.samuell.rhino.service.impl;

import com.samuell.rhino.controller.form_validation.ValidationHelpers;
import com.samuell.rhino.model.*;
import com.samuell.rhino.model.dto.BugDto;
import com.samuell.rhino.model.dto.BugHasSpecificationDto;
import com.samuell.rhino.model.dto.BugHasUserDto;
import com.samuell.rhino.model.dto.BugHasVersionDto;
import com.samuell.rhino.model.embedded_key.BugHasBugKey;
import com.samuell.rhino.model.embedded_key.BugHasSpecificationKey;
import com.samuell.rhino.model.embedded_key.BugHasUserKey;
import com.samuell.rhino.model.embedded_key.BugHasVersionKey;
import com.samuell.rhino.model.mapper.BugMapper;
import com.samuell.rhino.model.status_enum.LogStatus;
import com.samuell.rhino.repository.*;
import com.samuell.rhino.service.BugService;
import com.samuell.rhino.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BugServiceImpl implements BugService {
    @Autowired
    BugRepository bugRepository;
    @Autowired
    VersionRepository versionRepository;
    @Autowired
    SpecificationRepository specificationRepository;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LogService logService;
    @Autowired
    BugMapper bugMapper;

    @Override
    public List<BugDto> getAllBugsByProjectId(Integer projectId) {
        return bugRepository.findAll()
                .stream()
                .filter(bug ->
                        !bug.isWas_deleted() &&
                                bug.getProject().getId().equals(projectId))
                .map(bug -> bugMapper.toBugDto(bug)
                )
                .collect(Collectors.toList());
    }

    @Override
    public BugDto getBugById(Integer projectId, Integer bugId) {
        return bugMapper.toBugDto(bugRepository.findById(bugId)
                .filter(bug ->
                        bug.getProject().getId().equals(projectId) &&
                        !bug.isWas_deleted())
                .orElse(null));
    }

    @Override
    public Bug addBug(Integer projectId, BugDto bugDto) {
        Bug bug = new Bug();

        bug.setSummarize(bugDto.getSummarize());
        bug.setDescription(bugDto.getDescription());
        bug.setAdditional_info(bugDto.getAdditional_info());
        bug.setSeek_time(bugDto.getSeek_time());
        bug.setProject(projectRepository.findById(projectId).orElse(null));
        bug.setCategory(categoryRepository.findById(bugDto.getCategory().getId()).orElse(null));
        bug.setCreated_at(Timestamp.from(Instant.now()));

        bugRepository.save(bug);

        bug.setBugHasUsers(bugDto.getBugHasUsers()
                .stream()
                .map(bugUser -> {
                    Bug newBug = bugRepository.findById(bug.getId()).orElse(null);
                    User newUser = userRepository.findById(bugUser.getUser().getId()).orElse(null);
                    BugHasUserKey newBugHasVersionKey = new BugHasUserKey(bugUser.getType(),bug.getId(), bugUser.getUser().getId());
                    BugHasUser newBugHasUser = new BugHasUser();

                    newBugHasUser.setId(newBugHasVersionKey);
                    newBugHasUser.setBug(newBug);
                    newBugHasUser.setUser(newUser);

                    return newBugHasUser;
                })
                .collect(Collectors.toSet()));

        bug.setBugHasVersions(bugDto.getBugHasVersions()
                .stream()
                .map(bugVersion -> {
                    Bug newBug = bugRepository.findById(bug.getId()).orElse(null);
                    Version newVersion = versionRepository.findById(bugVersion.getVersion().getId()).orElse(null);
                    BugHasVersionKey newBugHasVersionKey = new BugHasVersionKey(bugVersion.getType(),bug.getId(), bugVersion.getVersion().getId());
                    BugHasVersion newBugHasVersion = new BugHasVersion();

                    newBugHasVersion.setId(newBugHasVersionKey);
                    newBugHasVersion.setBug(newBug);
                    newBugHasVersion.setVersion(newVersion);

                    return newBugHasVersion;
                })
                .collect(Collectors.toSet()));

        bug.setBugHasSpecifications(bugDto.getBugHasSpecifications()
                .stream()
                .map(bugSpecification -> {
                    Bug newBug = bugRepository.findById(bug.getId()).orElse(null);
                    Specification newSpecification = specificationRepository.findById(bugSpecification.getSpecification().getId()).orElse(null);
                    BugHasSpecificationKey newBugHasSpecificationKey = new BugHasSpecificationKey(bug.getId(), bugSpecification.getSpecification().getId());
                    BugHasSpecification newBugHasSpecification = new BugHasSpecification();

                    newBugHasSpecification.setId(newBugHasSpecificationKey);
                    newBugHasSpecification.setBug(newBug);
                    newBugHasSpecification.setSpecification(newSpecification);

                    return newBugHasSpecification;
                })
                .collect(Collectors.toSet()));

        bug.setBugHasBugsContains(bugDto.getBugHasBugsContains()
                .stream()
                .map(bugHasBug -> {
                    Bug newBugContains = bugRepository.findById(bug.getId()).orElse(null);
                    Bug newBugIncluded = bugRepository.findById(bugHasBug.getIncluded().getId()).orElse(null);
                    BugHasBugKey newBugHasBugKey = new BugHasBugKey(newBugContains.getId(), newBugIncluded.getId());
                    BugHasBug newBugHasBug = new BugHasBug(newBugHasBugKey, bugHasBug.getStatus(), newBugContains, newBugIncluded);

                    return newBugHasBug;
                })
                .collect(Collectors.toSet()));

        return bugRepository.save(bug);
    }

    @Override
    public Bug updateBug(Integer projectId, Integer bugId, BugDto bugDto, Set<BugHasUser> oldBugHasUserSet, Set<BugHasVersion> oldBugHasVersionSet, Set<BugHasSpecification> oldBugHasSpecificationSet, Set<BugHasBug> oldBugHasBugSet) {
        Bug bug = bugRepository.findById(bugId).orElse(new Bug());
        HashMap<String, LogStatus> atributesChanges = new HashMap<>();
        LogStatus logStatus;

        bug.setSummarize(bugDto.getSummarize());
        bug.setDescription(bugDto.getDescription());
        bug.setAdditional_info(bugDto.getAdditional_info());
        bug.setSeek_time(bugDto.getSeek_time());
        bug.setProject(projectRepository.findById(projectId).orElse(null));
        bug.setEdited_at(Timestamp.from(Instant.now()));

        bug.setBugHasBugsContains(bugDto.getBugHasBugsContains()
                .stream()
                .map(bugHasBug -> {
                    Bug newBugContains = bugRepository.findById(bug.getId()).orElse(null);
                    Bug newBugIncluded = bugRepository.findById(bugHasBug.getIncluded().getId()).orElse(null);
                    BugHasBugKey newBugHasBugKey = new BugHasBugKey(newBugContains.getId(), newBugIncluded.getId());
                    BugHasBug newBugHasBug = new BugHasBug(newBugHasBugKey, bugHasBug.getStatus(), newBugContains, newBugIncluded);

                    return newBugHasBug;
                })
                .collect(Collectors.toSet()));

        //Atributes with loging changes:
        //-> Category
        if (!bug.getCategory().getId().equals(bugDto.getCategory().getId())) {
            atributesChanges.put(
                    bug.getCategory().getName() + " -> " + bugDto.getCategory().getName(),
                    LogStatus.CATEGORY_CHANGE);
        }
        bug.setCategory(categoryRepository.findById(bugDto.getCategory().getId()).orElse(null));

        //-> Versions
        bug.setBugHasVersions(bugDto.getBugHasVersions()
                .stream()
                .map(bugVersion -> {
                    Bug newBug = bugRepository.findById(bug.getId()).orElse(null);
                    Version newVersion = versionRepository.findById(bugVersion.getVersion().getId()).orElse(null);
                    BugHasVersionKey newBugHasVersionKey = new BugHasVersionKey(bugVersion.getType(),newBug.getId(), newVersion.getId());
                    BugHasVersion newBugHasVersion = new BugHasVersion();

                    newBugHasVersion.setId(newBugHasVersionKey);
                    newBugHasVersion.setBug(newBug);
                    newBugHasVersion.setVersion(newVersion);

                    //Logs
                    for (Iterator<BugHasVersion> it = oldBugHasVersionSet.iterator(); it.hasNext();) {
                        BugHasVersion bugHasVersionIt = it.next();
                        if (!bugHasVersionIt.getVersion().getId().equals(bugVersion.getVersion().getId()) && bugHasVersionIt.getId().getType().equals(bugVersion.getType())){
                            atributesChanges.put(
                                    bugHasVersionIt.getVersion().getName()+ " -> " + bugVersion.getVersion().getName(),
                                    setLogStatusFromString(bugVersion.getType())
                            );
                        }
                    }
                    return newBugHasVersion;
                })
                .collect(Collectors.toSet()));

        //-> Users
        bug.setBugHasUsers(bugDto.getBugHasUsers()
                .stream()
                .map(bugUser -> {
                    Bug newBug = bugRepository.findById(bug.getId()).orElse(null);
                    User newUser = userRepository.findById(bugUser.getUser().getId()).orElse(null);
                    BugHasUserKey newBugHasUserKey = new BugHasUserKey(bugUser.getType(),newBug.getId(), newUser.getId());
                    BugHasUser newBugHasUser = new BugHasUser();

                    newBugHasUser.setId(newBugHasUserKey);
                    newBugHasUser.setBug(newBug);
                    newBugHasUser.setUser(newUser);

                    for (Iterator<BugHasUser> it = oldBugHasUserSet.iterator(); it.hasNext();) {
                        BugHasUser bugHasUserIt = it.next();
                        if (!bugHasUserIt.getUser().getId().equals(bugUser.getUser().getId()) && bugHasUserIt.getId().getType().equals(bugUser.getType())){
                            atributesChanges.put(
                                    bugHasUserIt.getUser().getName()+ " -> " + bugUser.getUser().getName(),
                                    setLogStatusFromString(bugUser.getType())
                            );
                        }
                    }
                    return newBugHasUser;
                })
                .collect(Collectors.toSet()));

        //-> Specifications
        bug.setBugHasSpecifications(bugDto.getBugHasSpecifications()
                .stream()
                .map(bugSpecification -> {
                    Bug newBug = bugRepository.findById(bug.getId()).orElse(null);
                    Specification newSpecification = specificationRepository.findById(bugSpecification.getSpecification().getId()).orElse(null);
                    BugHasSpecificationKey newBugHasSpecificationKey = new BugHasSpecificationKey(newBug.getId(), newSpecification.getId());
                    BugHasSpecification newBugHasSpecification = new BugHasSpecification();

                    newBugHasSpecification.setId(newBugHasSpecificationKey);
                    newBugHasSpecification.setBug(newBug);
                    newBugHasSpecification.setSpecification(newSpecification);

                    for (Iterator<BugHasSpecification> it = oldBugHasSpecificationSet.iterator(); it.hasNext(); ) {
                        BugHasSpecification bugSpecificationIt = it.next();
                        if (!bugSpecificationIt.getSpecification().getId().equals(bugSpecification.getSpecification().getId()) && bugSpecificationIt.getSpecification().getType().equals(bugSpecification.getSpecification().getType()))
                        atributesChanges.put(
                                bugSpecificationIt.getSpecification().getName() + " ->" + bugSpecification.getSpecification().getName(),
                                setLogStatusFromString(bugSpecification.getSpecification().getType()));
                    }

                    return newBugHasSpecification;
                })
                .collect(Collectors.toSet()));

        //Save logs of changed bug´s attributes
        creteLogsOfChangedAttributesBeforeBugUpdate(bugId,1,atributesChanges);

        return bugRepository.save(bug);
    }

    @Override
    public Bug deleteBug(Integer projectId, Integer bugId) {
        Bug bug = bugRepository.findById(bugId).orElse(new Bug());
        bug.setWas_deleted(true);

        return bugRepository.save(bug);
    }

    @Override
    public Map<String, String> validateBug(BugDto bugDto) {
        Map<String,String> errors = new HashMap<>();
        Boolean hasFoundInVersion, hasAssociatedUser, hasStatus, hasPriority, hasReproducibility;
        hasFoundInVersion = hasAssociatedUser = hasStatus = hasPriority = hasReproducibility = false;

        if(bugDto.getSummarize() == null || !bugDto.getSummarize().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("summarize","Zadajte sumarizáciu");

        if(bugDto.getDescription() == null || !bugDto.getDescription().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("description","Zadajte popis");

        if(bugDto.getCategory() == null)
            errors.put("category","Vyberte kategóriu");

        for(BugHasSpecificationDto specification : bugDto.getBugHasSpecifications()){
            if(specification.getType().equals("Status") && specification.getSpecification() != null)
                hasStatus = true;
            if(specification.getType().equals("Priority") && specification.getSpecification() != null)
                hasPriority = true;
            if(specification.getType().equals("Reproducibility") && specification.getSpecification()!= null)
                hasReproducibility = true;
        }
        if (!hasStatus)
            errors.put("status","Vyberte status");
        if (!hasPriority)
            errors.put("priority","Vyberte prioritu");
        if (!hasReproducibility)
            errors.put("reproducibility","Vyberte reprodukovaľnosť");

        for(BugHasVersionDto version : bugDto.getBugHasVersions()){
            if(version.getType().equals("Found in version") && version.getVersion() != null)
                hasFoundInVersion = true;
        }
        if (!hasFoundInVersion)
            errors.put("foundInVersion","Vyberte verziu nájdenia");

        for(BugHasUserDto user : bugDto.getBugHasUsers()){
            if(user.getType().equals("Associated user") && user.getUser() != null)
                hasAssociatedUser = true;
        }
        if (!hasAssociatedUser)
            errors.put("associatedUser","Vyberte priradeného používateľa");

        return errors;
    }

    //Additional methods:
    //-> Method return LogStatus from type in String
    private LogStatus setLogStatusFromString(String type){
        switch (type) {
            case "Status":
                return LogStatus.STATUS_CHANGE;
            case "Priority":
                return LogStatus.PRIORITY_CHANGE;
            case "Reproducibility":
                return LogStatus.REPRODUCIBILITY_CHANGE;
            case "Found in version":
                return LogStatus.FOUND_IN_VERSION_CHANGE;
            case "Repaired in version":
                return LogStatus.REPAIRED_IN_VERSION_CHANGE;
            case "Associated user":
                return LogStatus.ASSOCIATED_USER_CHANGE;
            default:
                return LogStatus.DEFAULT;
        }
    }

    //-> Method create logs for each item in hashmap -> hashmap contains text of log and status (LogStatus) of log
    private void creteLogsOfChangedAttributesBeforeBugUpdate(Integer bugId, Integer idOfLastEditingUser, HashMap<String,LogStatus> atributesChanges){
        for(Map.Entry<String, LogStatus> entry : atributesChanges.entrySet()) {
            logService.addLog(bugId, idOfLastEditingUser, entry.getKey(), entry.getValue());
        }
    }
}

