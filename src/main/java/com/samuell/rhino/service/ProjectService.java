package com.samuell.rhino.service;

import com.samuell.rhino.model.Project;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProjectService {
    @Nullable
    List<Project> getAllProjects();

    @Nullable
    Project getProjectById(int id);

    @Nullable
    Project addProject(Project project);
}
