package com.samuell.rhino.controller;

import com.samuell.rhino.model.Bug;
import com.samuell.rhino.model.dto.BugDto;
import com.samuell.rhino.model.mapper.BugMapper;
import com.samuell.rhino.model.status_enum.LogStatus;
import com.samuell.rhino.repository.BugHasVersionRepository;
import com.samuell.rhino.repository.BugRepository;
import com.samuell.rhino.repository.VersionRepository;
import com.samuell.rhino.service.BugService;
import com.samuell.rhino.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class BugController {
    @Autowired
    BugMapper bugMapper;

    private final BugService bugService;
    private final LogService logService;
    private final BugRepository bugRepository;
    private final VersionRepository versionRepository;
    private final BugHasVersionRepository bugHasVersionRepository;

    public BugController(BugService bugService, LogService logService, BugRepository bugRepository, VersionRepository versionRepository, BugHasVersionRepository bugHasVersionRepository) {
        this.bugService = bugService;
        this.logService = logService;
        this.bugRepository = bugRepository;
        this.versionRepository = versionRepository;
        this.bugHasVersionRepository = bugHasVersionRepository;
    }

    @GetMapping("{projectId}/bug")
    public ResponseEntity<?> getAllBugsByProjectId(@PathVariable("projectId") Integer projectId) {
        List<BugDto> bugDtoList = bugService.getAllBugsByProjectId(projectId);

        return new ResponseEntity<>(bugDtoList, HttpStatus.OK);
    }

    @GetMapping("{projectId}/bug/{bugId}")
    public ResponseEntity<?> getBugById(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId) {
        BugDto bugDto = bugService.getBugById(projectId,bugId);

        if(bugDto == null){
            return new ResponseEntity<>("Bug not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(bugDto, HttpStatus.OK);
        }
    }

    @PostMapping("{projectId}/bug")
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

    @PostMapping("{projectId}/bug/{bugId}")
    public ResponseEntity<?> updateBug(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId, @RequestBody BugDto bugDto) {
        //TEST
//        Bug asdasdsa = bugRepository.findById(bugId).orElse(new Bug());
//        Bug x = new Bug();
//        x.setBugHasVersions(asdasdsa.getBugHasVersions());
//        Integer i = x.getBugHasVersions().size();
//        for (Iterator<BugHasVersion> it = x.getBugHasVersions().iterator(); it.hasNext();) {
//            BugHasVersion bugHasVersionIt = it.next();
//            System.out.println(bugHasVersionIt.getVersion().getId());
//        }

        bugRepository.deleteSpecifications(bugId);
        bugRepository.deleteVersions(bugId);
        bugRepository.deleteBugHasBug(bugId);

        if(bugService.getBugById(projectId,bugId) == null){
            return new ResponseEntity<>("Bug not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Bug bug = bugService.updateBug(projectId,bugId,bugDto/*,newbugHasVersionSet*/);
            String logMessage = "Bug with id: " + bug.getId();

            logService.addLog(bug.getId(),bug.getUser().getId(),logMessage, LogStatus.BUG_EDIT);
            return ResponseEntity.status(HttpStatus.OK).body("Bug with ID: "+ bug.getId() + " was updated");
        }

    }

    @DeleteMapping("{projectId}/bug/{bugId}")
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
