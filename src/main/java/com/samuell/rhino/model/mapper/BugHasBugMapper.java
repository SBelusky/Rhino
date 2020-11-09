package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Bug;
import com.samuell.rhino.model.BugHasBug;
import com.samuell.rhino.model.Specification;
import com.samuell.rhino.model.dto.BugDto;
import com.samuell.rhino.model.dto.BugHasBugDto;
import com.samuell.rhino.model.dto.SpecificationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BugHasBugMapper {
    BugHasBugMapper INSTANCE = Mappers.getMapper(BugHasBugMapper.class);

    @Mapping(target = "contains", ignore=true)
    BugHasBugDto toBugHasBugDto(BugHasBug bugHasBug);
    BugHasBug toBugHasBug(BugHasBugDto bugHasBugDto);

    @Mappings({
        @Mapping(target = "description", ignore=true),
        @Mapping(target = "additional_info", ignore=true),
        @Mapping(target = "seek_time", ignore=true),
        @Mapping(target = "bugHasVersions", ignore=true),
        @Mapping(target = "project", ignore=true),
        @Mapping(target = "category", ignore=true),
        @Mapping(target = "user", ignore=true),
        @Mapping(target = "created_at", ignore=true),
        @Mapping(target = "edited_at", ignore=true),
    })
    BugDto toBugDto(Bug bug);
    Bug toBug(BugDto bugDto);

    @Mappings({
            @Mapping(target = "description", ignore=true),
            @Mapping(target = "created_at", ignore=true)
    })
    SpecificationDto toSpecificationDto(Specification specification);
    Specification toSpecification(SpecificationDto specificationDto);


}
