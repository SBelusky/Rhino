package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.*;
import com.samuell.rhino.model.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BugMapper {
    BugMapper INSTANCE = Mappers.getMapper(BugMapper.class);
    //Bug - clear
    BugDto toBugDto(Bug bug);
    Bug toBug(BugDto bugDto);

    //Project - clear
    @Mappings({
            @Mapping(target = "description", ignore=true),
            @Mapping(target = "user_has_projects", ignore=true),
            @Mapping(target = "created_at", ignore=true),
    })
    ProjectDto toProjectDto(Project project);
    Project toProject(ProjectDto projectDto);

    //Category - clear
    @Mappings({
            @Mapping(target = "description", ignore=true),
            @Mapping(target = "project", ignore=true),
            @Mapping(target = "created_at", ignore=true),
    })
    CategoryDto toCategoryDto(Category category);
    Category toCategory(CategoryDto categoryDto);

    //User - clear
    @Mappings({
            @Mapping(target = "telephone_number", ignore=true),
            @Mapping(target = "login_name", ignore=true),
            @Mapping(target = "login_password", ignore=true),
            @Mapping(target = "role", ignore=true),
            @Mapping(target = "created_at", ignore=true),
    })
    UserDto toUserDto(User user);
    User toUser(UserDto userDto);

    //BugHasVersion - clear
    @Mapping(target="bug", ignore=true)
    BugHasVersionDto toBugHasVersionDto(BugHasVersion bugHasVersion);

    @Mapping(target="bug", ignore=true)
    BugHasVersion toBugHasVersion(BugHasVersionDto bugHasVersionDto);

            //Version - clear
            @Mappings({
                    @Mapping(target = "description", ignore=true),
                    @Mapping(target = "created_at", ignore=true),
                    @Mapping(target = "project", ignore=true)
            })
            VersionDto toVersionDto(Version version);
            Version toVersion(VersionDto versionDto);

    //BugHasSpecification
    @Mapping(target="bug", ignore=true)
    BugHasSpecificationDto toBugHasSpecificationDto(BugHasSpecification bugHasSpecification);

    @Mapping(target="bug", ignore=true)
    BugHasSpecification toBugHasSpecification(BugHasSpecificationDto bugHasSpecificationDto);

            //Specification - clear
            @Mappings({
                    @Mapping(target = "description", ignore=true),
                    @Mapping(target = "created_at", ignore=true)
            })
            SpecificationDto toSpecificationDto(Specification specification);
            Specification toSpecification(SpecificationDto specificationDto);

    //BugHasBug
    @Mapping(target="included", ignore=true)
    BugHasBugDto toBugHasBugDto(BugHasBug bugHasBug);

    @Mapping(target="included", ignore=true)
    BugHasBug toBugHasBug(BugHasBugDto bugHasBugDto);

            //BugContains - clear
            @Mappings({
                    @Mapping(target = "description", ignore=true),
                    @Mapping(target = "created_at", ignore=true)
            })
            BugContainsDto toBugContainsDto(Specification specification);
            //Specification toSpecification(SpecificationDto specificationDto);

}
