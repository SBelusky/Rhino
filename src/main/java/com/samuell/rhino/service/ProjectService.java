package com.samuell.rhino.service;

import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.dto.ProjectDto;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProjectService {
    @Nullable
    List<ProjectDto> getAllProjects();

    @Nullable
    ProjectDto getProjectById(Integer id);

    @Nullable
    Project addProject(ProjectDto projectDto);

    @Nullable
    Project updateProject(Integer id, ProjectDto projectDto);

    @Nullable
    Project deleteProject(Integer id);
}
