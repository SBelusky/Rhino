package com.samuell.rhino.controller;

import com.samuell.rhino.model.Version;
import com.samuell.rhino.model.dto.VersionDto;
import com.samuell.rhino.service.VersionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project/")
public class VersionController {

    private final VersionService versionService;

    public VersionController(VersionService versionService) {
        this.versionService = versionService;
    }

    @GetMapping("/{projectId}/version")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllVersionsByProjectId(@PathVariable("projectId") Integer projectId) {
        List<VersionDto> versionDtoList = versionService.getAllVersionsByProjectId(projectId);

        return new ResponseEntity<>(versionDtoList, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/version/{versionId}")
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

    @PostMapping("/{projectId}/version")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addVersion(@PathVariable("projectId") Integer projectId, @RequestBody VersionDto versionDto) {
        Version version = versionService.addVersion(projectId, versionDto);

        if(version == null){
            return new ResponseEntity<>("Error while creating version",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Version created with ID: "+ version.getId());
        }
    }

    @PostMapping("/{projectId}/version/{versionId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateVersion(@PathVariable("projectId") Integer projectId, @PathVariable("versionId") Integer versionId, @RequestBody VersionDto versionDto) {
        if(versionService.getVersionById(projectId, versionId) == null){
            return new ResponseEntity<>("Version not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Version version = versionService.updateVersion(projectId, versionId ,versionDto);

            return ResponseEntity.status(HttpStatus.OK).body("Version with ID: "+ version.getId() + " was updated");
        }
    }

    @DeleteMapping("/{projectId}/version/{versionId}")
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
