package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.dto.SpecificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SpecificationMapper {
    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    SpecificationDto toSpecificationDto(Specification specification);

    Specification toSpecification(SpecificationDto specificationDto);
}
