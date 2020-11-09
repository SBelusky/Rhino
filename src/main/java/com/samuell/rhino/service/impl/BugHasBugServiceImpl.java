package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Bug;
import com.samuell.rhino.model.BugHasBug;
import com.samuell.rhino.model.dto.BugHasBugDto;
import com.samuell.rhino.model.mapper.BugHasBugMapper;
import com.samuell.rhino.repository.BugHasBugRepository;
import com.samuell.rhino.repository.BugRepository;
import com.samuell.rhino.service.BugHasBugService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BugHasBugServiceImpl implements BugHasBugService {
    @Autowired
    BugHasBugRepository bugHasBugRepository;
    @Autowired
    BugRepository bugRepository;
    @Autowired
    BugHasBugMapper bugHasBugMapper;

    @Override
    public List<BugHasBugDto> getAllIncludedBugsByBugId(Integer projectId, Integer bugId) {
        return bugHasBugRepository.findAll()
                .stream()
                .filter(bugHasBug ->
                        bugHasBug.getContains().getId().equals(bugId) &&
                        bugHasBug.getContains().getProject().getId().equals(projectId))
                .map(bugHasBug -> bugHasBugMapper.toBugHasBugDto(bugHasBug))
                .collect(Collectors.toList());
    }

    @Override
    public Bug addIncludedBug(Integer projectId, Integer bugId, BugHasBugDto bugHasBugDto) {
        BugHasBug bugHasBug = bugHasBugMapper.toBugHasBug(bugHasBugDto);
        Bug editingBug = bugRepository.findById(bugId).orElse(null);
        Set<BugHasBug> includedBugsInEditingBug = new HashSet<>();

        if(bugRepository.findById(bugId).filter(
                bug -> bug.getProject().getId().equals(projectId))
                .orElse(null) == null){
            return null;
        }
        else {
            bugHasBug.setContains(editingBug);
            bugHasBug.setIncluded(bugRepository.findById(bugId).orElse(null));
            bugHasBug.setStatus(bugHasBugDto.getStatus());

            includedBugsInEditingBug.addAll(editingBug.getBugHasBugsIncluded());
            includedBugsInEditingBug.add(bugHasBug);
            editingBug.setBugHasBugsIncluded(includedBugsInEditingBug);

            return bugRepository.save(editingBug);
        }
    }

    @Override
    public Bug deleteIncludedBug(Integer projectId, Integer bugId, BugHasBugDto bugHasBugDto) {
        BugHasBug bugHasBug = bugHasBugMapper.toBugHasBug(bugHasBugDto);
        Bug editingBug = bugRepository.findById(bugId).orElse(null);
        Set<BugHasBug> includedBugsInEditingBug = new HashSet<>();

        if(bugRepository.findById(bugId).filter(
                bug -> bug.getProject().getId().equals(projectId))
                .orElse(null) == null){
            return null;
        }
        else {
            bugHasBug.setContains(editingBug);
            bugHasBug.setIncluded(bugRepository.findById(bugId).orElse(null));
            bugHasBug.setStatus(bugHasBugDto.getStatus());

            includedBugsInEditingBug.addAll(editingBug.getBugHasBugsIncluded());
            //zisti ako zmazať item zo setu
            includedBugsInEditingBug.remove(bugHasBugDto);
            editingBug.setBugHasBugsIncluded(includedBugsInEditingBug);

            return bugRepository.save(editingBug);
        }
    }
}
