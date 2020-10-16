package com.samuell.rhino.service;

import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.Version;
import com.samuell.rhino.model.dto.SpecificationDto;
import com.samuell.rhino.model.dto.VersionDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface SpecificationService {
    @Nullable
    List<SpecificationDto> getAllSpecifications();

    @Nullable
    SpecificationDto getSpecificationById(Integer specificationId);

    @Nullable
    Specification addSpecification(SpecificationDto specificationDto);

    @Nullable
    Specification updateSpecification(Integer specificationId, SpecificationDto specificationDto);

    @Nullable
    Specification deleteSpecification(Integer specificationId);
}
