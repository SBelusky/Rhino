package com.samuell.rhino.controller;

import com.samuell.rhino.model.*;
import com.samuell.rhino.model.dto.BugDto;
import com.samuell.rhino.model.dto.BugHasBugDto;
import com.samuell.rhino.model.dto.BugHasUserDto;
import com.samuell.rhino.model.mapper.BugMapper;
import com.samuell.rhino.model.status_enum.LogStatus;
import com.samuell.rhino.repository.BugRepository;
import com.samuell.rhino.service.BugService;
import com.samuell.rhino.service.LogService;
import com.samuell.rhino.service.UserService;
import com.samuell.rhino.service.impl.BugServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("api/project/{projectId}/")
public class BugController {
    @PersistenceContext
    EntityManager entityManager;

    private final BugService bugService;
    private final LogService logService;
    private final UserService userService;
    private final BugRepository bugRepository;

    public BugController(BugService bugService, LogService logService, UserService userService, BugRepository bugRepository) {
        this.bugService = bugService;
        this.logService = logService;
        this.userService = userService;
        this.bugRepository = bugRepository;
    }

    @GetMapping("bug")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllBugsByProjectId(@PathVariable("projectId") Integer projectId) {
        List<BugDto> bugDtoList = bugService.getAllBugsByProjectId(projectId);

        return new ResponseEntity<>(bugDtoList, HttpStatus.OK);
    }

    @GetMapping("detail/bug/{bugId}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
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

    @PostMapping("add/bug")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addBug(@PathVariable("projectId") Integer projectId, @RequestBody BugDto bugDto) {
        Map<String,String> errors = bugService.validateBug(bugDto);
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); -> //vue+spring
        Integer authorId = 0;

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            Bug bug = bugService.addBug(projectId, bugDto);

            for (BugHasUserDto user: bugDto.getBugHasUsers()){
                if (user.getType().equals("Author"))
                    authorId = user.getUser().getId();
//                    authorId = userService.getUserById(userDetails.getId()).getId(); //vue+spring
            }

            String logMessage = "Report s ID: " + bugService.formatBugId(bug.getId());
            logService.addLog(bug.getId(),0,logMessage, LogStatus.BUG_CREATE);


            return ResponseEntity.status(HttpStatus.CREATED).body("Bug created with ID: "+ bug.getId());
        }
    }

    @PostMapping("edit/bug/{bugId}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateBug(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId, @RequestBody BugDto bugDto) {
        Map<String,String> errors = bugService.validateBug(bugDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            // deleting all data from many-to-many tables
            entityManager.clear();
            Bug oldBug = bugRepository.findById(bugId).orElse(new Bug());
            Set<BugHasVersion> oldBugHasVersionSet = new HashSet<>(oldBug.getBugHasVersions());
            Set<BugHasSpecification> oldBugHasSpecificationSet = new HashSet<>(oldBug.getBugHasSpecifications());
            Set<BugHasBug> oldBugHasBugSet = new HashSet<>(oldBug.getBugHasBugsContains());
            Set<BugHasUser> oldBugHasUserSet = new HashSet<>(oldBug.getBugHasUsers());

            oldBug = null;
            entityManager.clear();

            bugRepository.deleteSpecifications(bugId);
            bugRepository.deleteVersions(bugId);
            bugRepository.deleteBugHasBug(bugId);
            bugRepository.deleteUsers(bugId);
            // ----- //

            Bug bug = bugService.updateBug(projectId,bugId,bugDto,oldBugHasUserSet, oldBugHasVersionSet, oldBugHasSpecificationSet, oldBugHasBugSet);
            String logMessage = "Report s ID: " + bugService.formatBugId(bug.getId());

            logService.addLog(bug.getId(),1,logMessage, LogStatus.BUG_EDIT);
            return ResponseEntity.status(HttpStatus.OK).body("Bug with ID: "+ bug.getId() + " was updated");
        }

    }

    @DeleteMapping("delete/bug/{bugId}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
//    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> deleteBug(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId) {

        if(bugService.getBugById(projectId,bugId) == null){
            return new ResponseEntity<>("Bug not found",HttpStatus.NOT_FOUND);
        }
        else {
            Bug bug = bugService.deleteBug(projectId,bugId);
            String logMessage = "Report s ID: " +  bugService.formatBugId(bug.getId());

            logService.addLog(bugId,1,logMessage, LogStatus.BUG_DELETE);
            return ResponseEntity.status(HttpStatus.OK).body("Bug with ID: "+ bug.getId() + " was deleted");
        }
    }

    @PostMapping("bug/{bugId}/validate-relation")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> validateBugRelation(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId, @RequestBody BugHasBugDto bugHasBugDto) {
        Map<String,String> errors = bugService.validateRelationBug(bugHasBugDto, projectId, bugId);

        if(bugService.getBugById(projectId,bugId) == null){
            return new ResponseEntity<>("Bug not found",HttpStatus.NOT_FOUND);
        }
        else if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body("Added new relation to bug with ID: " + bugId);
       }
    }
}
