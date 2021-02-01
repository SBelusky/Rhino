package com.samuell.rhino.controller;

import com.samuell.rhino.model.dto.LogDto;
import com.samuell.rhino.service.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project/{projectId}/bug/{bugId}")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("{/log")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllLogsByBugId(@PathVariable("projectId") Integer projectId, @PathVariable("bugId") Integer bugId) {
        List<LogDto> logDtoList = logService.getAllLogsByBugId(projectId, bugId);

        return new ResponseEntity<>(logDtoList, HttpStatus.OK);
    }

}
