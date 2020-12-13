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
import java.util.Map;

@RestController
@RequestMapping("api/")
public class ProjectController{
    @Autowired
    ProjectMapper projectMapper;

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @GetMapping("project")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> getAllProjects() {
        List<ProjectDto> projectDtoList = projectService.getAllProjects();

        return new ResponseEntity<>(projectDtoList, HttpStatus.OK);
    }

    @GetMapping("detail/project/{id}")
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

    @PostMapping("add/project")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> addProject(@RequestBody ProjectDto projectDto) {
        Map<String,String> errors = projectService.validateProject(projectDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            Project project = projectService.addProject(projectDto);

            return ResponseEntity.status(HttpStatus.CREATED).body("Project created with ID: "+ project.getId());
        }
    }

    @PostMapping("edit/project/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> updateProject(@PathVariable("id") Integer id, @RequestBody ProjectDto projectDto) {
        Map<String,String> errors = projectService.validateProject(projectDto);

        if(errors.size() != 0){
            return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
        }
        else {
            projectRepository.deleteUsers(id);
            Project project = projectService.updateProject(id,projectDto);

            return ResponseEntity.status(HttpStatus.OK).body("Project with ID: "+ project.getId() + " was updated");
        }
    }

    @DeleteMapping("delete/project/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
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
