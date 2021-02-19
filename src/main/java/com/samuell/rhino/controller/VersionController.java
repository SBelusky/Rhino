package com.samuell.rhino.controller;

import com.samuell.rhino.model.Version;
import com.samuell.rhino.model.dto.VersionDto;
import com.samuell.rhino.service.VersionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/project/{projectId}/")
public class VersionController {

    private final VersionService versionService;

    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping("version")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllVersionsByProjectId(@PathVariable("projectId") Integer projectId) {
        List<VersionDto> versionDtoList = versionService.getAllVersionsByProjectId(projectId);

        return new ResponseEntity<>(versionDtoList, HttpStatus.OK);
    }

    @GetMapping("bug-version")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllVersionsByProjectIdForAllRoles(@PathVariable("projectId") Integer projectId) {
        List<VersionDto> versionDtoList = versionService.getAllVersionsByProjectId(projectId);

        return new ResponseEntity<>(versionDtoList, HttpStatus.OK);
    }

    @GetMapping("detail/version/{versionId}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getVersionById(@PathVariable("projectId") Integer projectId, @PathVariable("versionId") Integer versionId) {
        VersionDto versionDto = versionService.getVersionById(projectId,versionId);

        if(versionDto == null){
            return new ResponseEntity<>("Version not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(versionDto, HttpStatus.OK);
        }
    }

    @PostMapping("add/version")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addVersion(@PathVariable("projectId") Integer projectId, @RequestBody VersionDto versionDto) {
        Map<String,String> errors = versionService.validateVersion(projectId,versionDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            Version version = versionService.addVersion(projectId, versionDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Version created with ID: "+ version.getId());
        }
    }

    @PostMapping("edit/version/{versionId}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateVersion(@PathVariable("projectId") Integer projectId, @PathVariable("versionId") Integer versionId, @RequestBody VersionDto versionDto) {
        Map<String,String> errors = versionService.validateVersion(projectId,versionDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            Version version = versionService.updateVersion(projectId, versionId ,versionDto);

            return ResponseEntity.status(HttpStatus.OK).body("Version with ID: "+ version.getId() + " was updated");
        }
    }

    @DeleteMapping("delete/version/{versionId}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> deleteVersion(@PathVariable("projectId") Integer projectId,@PathVariable("versionId") Integer versionId) {
        if(versionService.getVersionById(projectId,versionId) == null){
            return new ResponseEntity<>("Version not found",HttpStatus.NOT_FOUND);
        }
        else {
            Version version = versionService.deleteVersion(versionId);

            return ResponseEntity.status(HttpStatus.OK).body("Version with ID: "+ version.getId() + " was deleted");
        }
    }

}
