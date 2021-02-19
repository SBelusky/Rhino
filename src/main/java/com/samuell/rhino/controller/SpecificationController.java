package com.samuell.rhino.controller;

import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.dto.SpecificationDto;
import com.samuell.rhino.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/")
public class SpecificationController {
    private final SpecificationService specificationService;

    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @GetMapping("specification")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllSpecifications(@RequestParam(required = false) String type) {
        List<SpecificationDto> specificationDtoList = specificationService.getAllSpecificationByType(type);

        return new ResponseEntity<>(specificationDtoList, HttpStatus.OK);
    }

    @GetMapping("bug-specification")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_PROGRAMMER','ROLE_TESTER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllSpecificationsForAllRoles(@RequestParam(required = false) String type) {
        List<SpecificationDto> specificationDtoList = specificationService.getAllSpecificationByType(type);

        return new ResponseEntity<>(specificationDtoList, HttpStatus.OK);
    }

    @GetMapping("detail/specification/{specificationId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getSpecificationById(@PathVariable("specificationId") Integer specificationId) {
        SpecificationDto specificationDto = specificationService.getSpecificationById(specificationId);

        if(specificationDto == null){
            return new ResponseEntity<>("Specification not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(specificationDto, HttpStatus.OK);
        }
    }

    @PostMapping("add/specification")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addSpecification(@RequestBody SpecificationDto specificationDto) {
        Map<String,String> errors = specificationService.validateSpecification(specificationDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            Specification specification = specificationService.addSpecification(specificationDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Specification created with ID: "+ specification.getId());
        }
    }

    @PostMapping("edit/specification/{specificationId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateSpecification(@PathVariable("specificationId") Integer specificationId,@RequestBody SpecificationDto specificationDto) {
        Map<String,String> errors = specificationService.validateSpecification(specificationDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            Specification specification = specificationService.updateSpecification(specificationId, specificationDto);

            return ResponseEntity.status(HttpStatus.OK).body("Specification with ID: "+ specification.getId() + " was updated");
        }
    }

    @DeleteMapping("delete/specification/{specificationId}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> deleteSpecification(@PathVariable("specificationId") Integer specificationId) {
        if(specificationService.getSpecificationById(specificationId) == null){
            return new ResponseEntity<>("Specification not found",HttpStatus.NOT_FOUND);
        }
        else {
            Specification specification = specificationService.deleteSpecification(specificationId);

            return ResponseEntity.status(HttpStatus.OK).body("Specification with ID: "+ specification.getId() + " was deleted");
        }
    }
}
