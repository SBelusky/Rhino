package com.samuell.rhino.controller;

import com.samuell.rhino.model.Bug;
import com.samuell.rhino.model.BugHasBug;
import com.samuell.rhino.model.BugHasSpecification;
import com.samuell.rhino.model.BugHasVersion;
import com.samuell.rhino.model.dto.BugDto;
import com.samuell.rhino.model.mapper.BugMapper;
import com.samuell.rhino.model.status_enum.LogStatus;
import com.samuell.rhino.repository.BugRepository;
import com.samuell.rhino.service.BugService;
import com.samuell.rhino.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/project/{projectId}")
public class BugController {
    @Autowired
    BugMapper bugMapper;
    @PersistenceContext
    EntityManager entityManager;

    private final BugService bugService;
    private final LogService logService;
    private final BugRepository bugRepository;

    public BugController(BugService bugService, LogService logService, BugRepository bugRepository) {
        this.bugService = bugService;
        this.logService = logService;
        this.bugRepository = bugRepository;
    }

    @GetMapping("/bug")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllBugsByProjectId(@PathVariable("projectId") Integer projectId) {
        List<BugDto> bugDtoList = bugService.getAllBugsByProjectId(projectId);

        return new ResponseEntity<>(bugDtoList, HttpStatus.OK);
    }

    @GetMapping("/bug/{bugId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getBugById(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId) {
        BugDto bugDto = bugService.getBugById(projectId,bugId);

        if(bugDto == null){
            return new ResponseEntity<>("Bug not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(bugDto, HttpStatus.OK);
        }
    }

    @PostMapping("/add/bug")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addBug(@PathVariable("projectId") Integer projectId, @RequestBody BugDto bugDto) {
        Bug bug = bugService.addBug(projectId, bugDto);

        if(bug == null){
            return new ResponseEntity<>("Error while creating bug",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            String logMessage = "Bug with id: " + bug.getId();

            logService.addLog(bug.getId(),bug.getUser().getId(),logMessage, LogStatus.BUG_CREATE);
            return ResponseEntity.status(HttpStatus.CREATED).body("Bug created with ID: "+ bug.getId());
        }
    }

    @PostMapping("/edit/bug/{bugId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateBug(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId, @RequestBody BugDto bugDto) {
        //TEST
        entityManager.clear();
        Bug oldBug = bugRepository.findById(bugId).orElse(new Bug());
        Set<BugHasVersion> oldBugHasVersionSet = new HashSet<>(oldBug.getBugHasVersions());
        Set<BugHasSpecification> oldBugHasSpecificationSet = new HashSet<>(oldBug.getBugHasSpecifications());;
        Set<BugHasBug> oldBugHasBugSet = new HashSet<>(oldBug.getBugHasBugsContains());;

        oldBug = null;
        entityManager.clear();




        bugRepository.deleteSpecifications(bugId);
        bugRepository.deleteVersions(bugId);
        bugRepository.deleteBugHasBug(bugId);

        if(bugService.getBugById(projectId,bugId) == null){
            return new ResponseEntity<>("Bug not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Bug bug = bugService.updateBug(projectId,bugId,bugDto,oldBugHasVersionSet, oldBugHasSpecificationSet, oldBugHasBugSet);
            String logMessage = "Bug with id: " + bug.getId();

            logService.addLog(bug.getId(),bug.getUser().getId(),logMessage, LogStatus.BUG_EDIT);
            return ResponseEntity.status(HttpStatus.OK).body("Bug with ID: "+ bug.getId() + " was updated");
        }

    }

    @DeleteMapping("/delete/bug/{bugId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> deleteBug(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId, @RequestBody BugDto bugDto) {
        if(bugService.getBugById(projectId,bugId) == null){
            return new ResponseEntity<>("Bug not found",HttpStatus.NOT_FOUND);
        }
        else {
            Bug bug = bugService.deleteBug(projectId,bugId);
            String logMessage = "Bug with id: " + bug.getId();

            logService.addLog(bugId,bugDto.getIdOfLastEditingUser(),logMessage, LogStatus.BUG_DELETE);
            return ResponseEntity.status(HttpStatus.OK).body("Bug with ID: "+ bug.getId() + " was deleted");
        }
    }
}
