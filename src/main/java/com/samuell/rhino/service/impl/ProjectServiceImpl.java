package com.samuell.rhino.service.impl;

import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.UserHasProject;
import com.samuell.rhino.model.dto.ProjectDto;
import com.samuell.rhino.model.embedded_key.UserHasProjectKey;
import com.samuell.rhino.model.mapper.ProjectMapper;
import com.samuell.rhino.repository.ProjectRepository;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .filter(project -> !project.isWas_deleted())
                .map(project -> projectMapper.toProjectDto(project))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(Integer id) {
        return projectMapper.toProjectDto(projectRepository.findById(id).orElse(null));
    }

    @Override
    public Project addProject(ProjectDto projectDto) {
        Project project = projectMapper.toProject(projectDto);
        project.setCreated_at(Timestamp.from(Instant.now()));

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Integer id, ProjectDto projectDto) {
        Project project = projectRepository.findById(id).orElse(new Project());

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setUser_has_projects(projectDto.getUser_has_projects()
                .stream()
                .map(userProject -> {
                    User newuser = userRepository.findById(userProject.getId().getUser_id()).orElse(null);
                    Project newproject = projectRepository.findById(id).orElse(null);
                    UserHasProjectKey newuserHasProjectKey = new UserHasProjectKey(userProject.getId().getUser_id(),userProject.getId().getProject_id());
                    UserHasProject newuserHasProject = new UserHasProject();

                    newuserHasProject.setId(newuserHasProjectKey);
                    newuserHasProject.setUser(newuser);
                    newuserHasProject.setProject(newproject);

                    return newuserHasProject;
                })
                .collect(Collectors.toSet()));
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

