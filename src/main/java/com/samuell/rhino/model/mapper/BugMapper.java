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
    //Bug
    @Mapping(target = "bugHasBugsIncluded", ignore = true)
    BugDto toBugDto(Bug bug);
    Bug toBug(BugDto bugDto);

    //Project
    @Mappings({
            @Mapping(target = "description", ignore=true),
            @Mapping(target = "user_has_projects", ignore=true),
            @Mapping(target = "created_at", ignore=true),
    })
    ProjectDto toProjectDto(Project project);
    Project toProject(ProjectDto projectDto);

    //Category
    @Mappings({
            @Mapping(target = "description", ignore=true),
            @Mapping(target = "project", ignore=true),
            @Mapping(target = "created_at", ignore=true),
    })
    CategoryDto toCategoryDto(Category category);
    Category toCategory(CategoryDto categoryDto);

    //BugHasVersion
    @Mapping(target="id", ignore=true)
    @Mapping(target="bug", ignore=true)
    BugHasVersionDto toBugHasVersionDto(BugHasVersion bugHasVersion);
    @Mapping(target="bug", ignore=true)
    BugHasVersion toBugHasVersion(BugHasVersionDto bugHasVersionDto);

            //Version
            @Mappings({
                    @Mapping(target = "description", ignore=true),
                    @Mapping(target = "created_at", ignore=true),
                    @Mapping(target = "project", ignore=true)
            })
            VersionDto toVersionDto(Version version);
            Version toVersion(VersionDto versionDto);

    //BugHasUser
    @Mapping(target="bug", ignore=true)
    @Mapping(target="id", ignore=true)
    BugHasUserDto toBugHasUserDto(BugHasUser bugHasUser);
    @Mapping(target="bug", ignore=true)
    BugHasUser toBugHasUser(BugHasUserDto bugHasUserDto);

        //User
        @Mappings({
                @Mapping(target = "telephone_number", ignore=true),
                @Mapping(target = "login_name", ignore=true),
                @Mapping(target = "login_password", ignore=true),
                @Mapping(target = "role", ignore=true),
                @Mapping(target = "created_at", ignore=true),
        })
        UserDto toUserDto(User user);
        User toUser(UserDto userDto);

    //BugHasSpecification
    @Mapping(target="bug", ignore=true)
    @Mapping(target="id", ignore=true)
    BugHasSpecificationDto toBugHasSpecificationDto(BugHasSpecification bugHasSpecification);
    BugHasSpecification toBugHasSpecification(BugHasSpecificationDto bugHasSpecificationDto);

            //Specification
            @Mappings({
                    @Mapping(target = "description", ignore=true),
                    @Mapping(target = "created_at", ignore=true)
            })
            SpecificationDto toSpecificationDto(Specification specification);
            Specification toSpecification(SpecificationDto specificationDto);

    //BugHasBug
    @Mapping(target="contains", ignore=true)
    @Mapping(target="included", ignore=true)
    BugHasBugDto toBugHasBugDto(BugHasBug bugHasBug);
    BugHasBug toBugHasBug(BugHasBugDto bugHasBugDto);
}
