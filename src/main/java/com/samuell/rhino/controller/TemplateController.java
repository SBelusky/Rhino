package com.samuell.rhino.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RequestMapping("/")
public class TemplateController {

    @PostMapping("login")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getLoginView(){

        return new ResponseEntity<>("bugDtoList", HttpStatus.OK);
    }

}