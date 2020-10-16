package com.samuell.rhino.controller;

import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.dto.SpecificationDto;
import com.samuell.rhino.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/specification")
public class SpecificationController {
    private final SpecificationService specificationService;

    public SpecificationController(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllSpecifications() {
        List<SpecificationDto> specificationDtoList = specificationService.getAllSpecifications();

        return new ResponseEntity<>(specificationDtoList, HttpStatus.OK);
    }

    @GetMapping("/{specificationId}")
    public ResponseEntity<?> getSpecificationById(@PathVariable("specificationId") Integer specificationId) {
        SpecificationDto specificationDto = specificationService.getSpecificationById(specificationId);

        if(specificationDto == null){
            return new ResponseEntity<>("Specification not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(specificationDto, HttpStatus.OK);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addSpecification(@RequestBody SpecificationDto specificationDto) {
        Specification specification = specificationService.addSpecification(specificationDto);

        if(specification == null){
            return new ResponseEntity<>("Error while creating specification",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Specification created with ID: "+ specification.getId());
        }
    }

    @PostMapping("/{specificationId}")
    public ResponseEntity<?> updateSpecification(@PathVariable("specificationId") Integer specificationId,@RequestBody SpecificationDto specificationDto) {
        if(specificationService.getSpecificationById(specificationId) == null){
            return new ResponseEntity<>("Specification not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Specification specification = specificationService.updateSpecification(specificationId, specificationDto);

            return ResponseEntity.status(HttpStatus.OK).body("Specification with ID: "+ specification.getId() + " was updated");
        }
    }

    @DeleteMapping("/{specificationId}")
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
