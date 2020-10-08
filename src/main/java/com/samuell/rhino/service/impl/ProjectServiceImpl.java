package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.UserHasProject;
import com.samuell.rhino.model.dto.ProjectDto;
import com.samuell.rhino.model.dto.UserHasProjectDto;
import com.samuell.rhino.model.mapper.ProjectMapper;
import com.samuell.rhino.model.mapper.UserHasProjectMapper;
import com.samuell.rhino.repository.ProjectRepository;
import com.samuell.rhino.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    UserHasProjectMapper userHasProjectMapper;

    @Override
    public List<ProjectDto> getAllProjects() {
        List<Project> projectList =  projectRepository.findAll();
        List<ProjectDto> projectDtoList = new ArrayList<>();
        System.out.println("a");
        for (Project project : projectList)
        {
            projectDtoList.add(new ProjectDto(
                    project.getId(),
                    project.getName(),
                    project.getDescription(),
                    userHasProjectMapper.UserHasProjectToUserHasProjectDto(project.getUser_has_projects())));
        }

        return projectDtoList;

//        return projectRepository.findAll().stream()
//                .map(project -> projectMapper.projectToProjectDto(project))
//                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(Integer id) {
        return projectMapper.projectToProjectDto(projectRepository.findById(id).orElse(null));
    }

    @Override
    public Project addProject(ProjectDto projectDto) {
        Project project = projectMapper.projectDtoToProject(projectDto);
        project.setCreated_at(Timestamp.from(Instant.now()));

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Integer id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id).orElse(new Project());

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
//        project.setUser_has_projects(
//            projectDto.getUser_has_projects().stream()
//                    .map(UserHasProjectDtoToUserHasProjectDto -> userHasProjectMapper.UserHasProjectDtoToUserHasProject(UserHasProjectDtoToUserHasProjectDto))
//                    .collect(Collectors.toSet())
//        );
        project.setEdited_at(Timestamp.from(Instant.now()));

        return projectRepository.save(project);
    }

    @Override
    public Project deleteProject(Integer id) {
        Project project = projectRepository.findById(id).orElse(new Project());
        project.setWas_deleted(true);

        return projectRepository.save(project);
    }
}
