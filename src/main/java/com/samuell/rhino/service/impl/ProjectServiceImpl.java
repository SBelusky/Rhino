package com.samuell.rhino.service.impl;

import com.samuell.rhino.controller.form_validation.ValidationHelpers;
import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.User;
import com.samuell.rhino.model.UserHasProject;
import com.samuell.rhino.model.dto.ProjectDto;
import com.samuell.rhino.model.dto.UserDto;
import com.samuell.rhino.model.embedded_key.UserHasProjectKey;
import com.samuell.rhino.model.mapper.ProjectMapper;
import com.samuell.rhino.repository.ProjectRepository;
import com.samuell.rhino.repository.UserRepository;
import com.samuell.rhino.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
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
    public ProjectDto getProjectById(Integer projectId) {
        return projectMapper.toProjectDto(projectRepository.findById(projectId).orElse(null));
    }

    @Override
    public List<ProjectDto> getAssignProjectsForUser(String username) {
        return projectRepository.findAll()
                .stream()
                .filter(project -> !project.isWas_deleted() &&
                        project.getUser_has_projects().stream()
                            .filter(userHasProject ->
                                    userHasProject.getUser().getUsername().equals(username))
                            .count() > 0
                        )
                .map(project -> projectMapper.toProjectDto(project))
                .collect(Collectors.toList());
    }

    @Override
    public Project addProject(ProjectDto projectDto) {
        Project project = new Project();

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setCreated_at(Timestamp.from(Instant.now()));

        projectRepository.save(project);

        project.setUser_has_projects(projectDto.getUser_has_projects()
                .stream()
                .map(userProject -> {
                    User newuser = userRepository.findById(userProject.getUser().getId()).orElse(null);
                    Project newproject = projectRepository.findById(project.getId()).orElse(null);
                    UserHasProjectKey newuserHasProjectKey = new UserHasProjectKey(userProject.getUser().getId(),newproject.getId());
                    UserHasProject newuserHasProject = new UserHasProject();

                    newuserHasProject.setId(newuserHasProjectKey);
                    newuserHasProject.setUser(newuser);
                    newuserHasProject.setProject(newproject);

                    return newuserHasProject;
                })
                .collect(Collectors.toSet()));


        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(Integer projectId, ProjectDto projectDto) {
        Project project = projectRepository.findById(projectId).orElse(new Project());

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setEdited_at(Timestamp.from(Instant.now()));

        project.setUser_has_projects(projectDto.getUser_has_projects()
                .stream()
                .map(userProject -> {
                    User newuser = userRepository.findById(userProject.getUser().getId()).orElse(null);
                    Project newproject = projectRepository.findById(projectId).orElse(null);
                    UserHasProjectKey newuserHasProjectKey = new UserHasProjectKey(userProject.getUser().getId(),newproject.getId());
                    UserHasProject newuserHasProject = new UserHasProject();

                    newuserHasProject.setId(newuserHasProjectKey);
                    newuserHasProject.setUser(newuser);
                    newuserHasProject.setProject(newproject);

                    return newuserHasProject;
                })
                .collect(Collectors.toSet()));

        return projectRepository.save(project);
    }

    @Override
    public Project deleteProject(Integer projectId) {
        Project project = projectRepository.findById(projectId).orElse(new Project());
        project.setWas_deleted(true);

        return projectRepository.save(project);
    }

    @Override
    public Map<String,String> validateProject(ProjectDto projectDto){
        Map<String,String> errors = new HashMap<>();

        if(projectDto.getName() == null || !projectDto.getName().matches(ValidationHelpers.NOT_BLANK_SPACES.pattern()))
            errors.put("name","Zadajte názov");

        return errors;
    }
}

