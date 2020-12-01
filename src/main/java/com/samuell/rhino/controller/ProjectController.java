package com.samuell.rhino.controller;

import com.samuell.rhino.model.Project;
import com.samuell.rhino.model.dto.ProjectDto;
import com.samuell.rhino.model.mapper.ProjectMapper;
import com.samuell.rhino.repository.ProjectRepository;
import com.samuell.rhino.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/project")
public class ProjectController{
    @Autowired
    ProjectMapper projectMapper;

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllProjects() {
        List<ProjectDto> projectDtoList = projectService.getAllProjects();

        return new ResponseEntity<>(projectDtoList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getProjectById(@PathVariable("id") Integer id) {
        ProjectDto projectDto = projectService.getProjectById(id);

        if(projectDto == null){
            return new ResponseEntity<>("Project not found",HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(projectDto, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<?> addProject(@RequestBody ProjectDto projectDto) {
        Project project = projectService.addProject(projectDto);

        if(project == null){
            return new ResponseEntity<>("Error while creating project",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body("Project created with ID: "+ project.getId());
        }
    }

    @PostMapping("edit/{id}")
    public ResponseEntity<?> updateProject(@PathVariable("id") Integer id, @RequestBody ProjectDto projectDto) {
        projectRepository.deleteUsers(id);

        if(projectService.getProjectById(id) == null){
            return new ResponseEntity<>("Project not found",HttpStatus.PRECONDITION_FAILED);
        }
        else {
            Project project = projectService.updateProject(id,projectDto);

            return ResponseEntity.status(HttpStatus.OK).body("Project with ID: "+ project.getId() + " was updated");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
        if(projectService.getProjectById(id) == null){
            return new ResponseEntity<>("Project not found",HttpStatus.NOT_FOUND);
        }
        else {
            Project project = projectService.deleteProject(id);

            return ResponseEntity.status(HttpStatus.OK).body("Project with ID: "+ project.getId() + " was deleted");
        }
    }
}
