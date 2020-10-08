package com.samuell.rhino.model.mapper;

import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto projectToProjectDto(Project project);

    Project projectDtoToProject(ProjectDto projectDto);
}
