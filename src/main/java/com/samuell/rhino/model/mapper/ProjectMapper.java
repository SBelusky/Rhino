package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.UserHasProject;
import com.samuell.rhino.model.dto.ProjectDto;
import com.samuell.rhino.model.dto.UserHasProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

//    @Mapping(target = "user_has_projects", ignore = true)
    ProjectDto toProjectDto(Project project);

    Project toProject(ProjectDto projectDto);

    @Mapping(target="project", ignore=true)
    UserHasProjectDto toUserHasProjectDto( UserHasProject userHasProject);

    @Mapping(target="project", ignore=true)
    UserHasProject toUserHasProject( UserHasProjectDto userHasProjectDto);
}