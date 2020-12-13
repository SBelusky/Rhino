package com.samuell.rhino.service;

import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.dto.ProjectDto;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    @Nullable
    List<ProjectDto> getAllProjects();

    @Nullable
    ProjectDto getProjectById(Integer projectId);

    @Nullable
    Project addProject(ProjectDto projectDto);

    @Nullable
    Project updateProject(Integer projectId, ProjectDto projectDto);

    @Nullable
    Project deleteProject(Integer projectId);

    @Nullable
    Map<String,String> validateProject(ProjectDto projectDto);
}
