package com.samuell.rhino.controller;

import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.dto.SpecificationDto;
import com.samuell.rhino.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/")
public class SpecificationController {
    private final SpecificationService specificationService;

    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @GetMapping("specification")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllSpecifications(@RequestParam(required = false) String type) {
        List<SpecificationDto> specificationDtoList = specificationService.getAllSpecificationByType(type);

        return new ResponseEntity<>(specificationDtoList, HttpStatus.OK);
    }

    @GetMapping("detail/specification/{specificationId}")
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
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addSpecification(@RequestBody SpecificationDto specificationDto) {
        Specification specification = specificationService.addSpecification(specificationDto);

        if(specification == null){
            return new ResponseEntity<>("Error while creating specification",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Specification created with ID: "+ specification.getId());
        }
    }

    @PostMapping("edit/specification/{specificationId}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateSpecification(@PathVariable("specificationId") Integer specificationId,@RequestBody SpecificationDto specificationDto) {
        if(specificationService.getSpecificationById(specificationId) == null){
            return new ResponseEntity<>("Specification not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Specification specification = specificationService.updateSpecification(specificationId, specificationDto);

            return ResponseEntity.status(HttpStatus.OK).body("Specification with ID: "+ specification.getId() + " was updated");
        }
    }

    @DeleteMapping("delete/specification/{specificationId}")
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
