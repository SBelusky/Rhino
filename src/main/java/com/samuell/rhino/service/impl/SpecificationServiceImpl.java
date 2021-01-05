package com.samuell.rhino.service.impl;

import com.samuell.rhino.controller.form_validation.ValidationHelpers;
import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.dto.SpecificationDto;
import com.samuell.rhino.model.dto.UserDto;
import com.samuell.rhino.model.mapper.SpecificationMapper;
import com.samuell.rhino.repository.SpecificationRepository;
import com.samuell.rhino.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
    SpecificationRepository specificationRepository;
    @Autowired
    SpecificationMapper specificationMapper;

    @Override
    public List<SpecificationDto> getAllSpecificationByType(String type) {
        return specificationRepository.findAll()
                .stream()
                .filter(specification ->
                        !specification.isWas_deleted() &&
                                specification.getType().equals(type))
                .map(specification -> specificationMapper.toSpecificationDto(specification))
                .collect(Collectors.toList());
    }

    @Override
    public SpecificationDto getSpecificationById(Integer specificationId) {
        return specificationMapper.toSpecificationDto(specificationRepository.findById(specificationId).orElse(null));
    }

    @Override
    public Specification addSpecification(SpecificationDto specificationDto) {
        Specification specification = specificationMapper.toSpecification(specificationDto);

        specification.setCreated_at(Timestamp.from(Instant.now()));

        return specificationRepository.save(specification);
    }

    @Override
    public Specification updateSpecification(Integer specificationId, SpecificationDto specificationDto) {
        Specification specification = specificationRepository.findById(specificationId).orElse(new Specification());

        specification.setName(specificationDto.getName());
        specification.setDescription(specificationDto.getDescription());
        specification.setType(specificationDto.getType());
        specification.setColor(specificationDto.getColor());
        specification.setEdited_at(Timestamp.from(Instant.now()));

        return specificationRepository.save(specification);
    }

    @Override
    public Specification deleteSpecification(Integer specificationId) {
        Specification specification = specificationRepository.findById(specificationId).orElse(new Specification());
        specification.setWas_deleted(true);

        return specificationRepository.save(specification);
    }

    @Override
    public Map<String, String> validateSpecification(SpecificationDto specificationDto) {
        Map<String, String> errors = new HashMap<>();

        if (specificationDto.getName() == null || !specificationDto.getName().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("name", "Zadajte názov");

        if (specificationDto.getType().equals("Status") && specificationDto.getColor().equals(""))
            errors.put("color", "Vyberte farbu");
        else if (specificationDto.getType().equals("Status") && !specificationDto.getColor().matches(ValidationHelpers.HEX_COLOR.pattern()))
            errors.put("color", "Farba je v nesprávnom tvare");
        else if (specificationRepository.findAll()
                .stream()
                .filter(specification ->
                        specification.getType().equals("Status") &&
                        !specification.getId().equals(specificationDto.getId()) && specification.getColor().equals(specificationDto.getColor()))
                .count() >= 1) {
            errors.put("color", "Farba je obsadená");
        }

        return errors;
    }
}
