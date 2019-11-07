package com.team3.dumb;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team3.model.Depart;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@GetMapping("projects")
	public ArrayList<Project> getAllProject() {
		return projectService.getAllProject();
	}

	@GetMapping("projects/{id}")
	public Optional<Project> getById(@PathVariable int id) {
		return projectService.getById(id);
	}

	@PostMapping("/projects")
	public void addProject(@RequestBody Project project) {
		projectService.addOrEditProject(project);
	}

	@PutMapping("/projects/{id}")
	public void editProject(@RequestBody Project project) {
		projectService.addOrEditProject(project);
	}

	@DeleteMapping("/projects/{id}")
	public void deleteProject(@PathVariable Integer id) {
		projectService.deleteProject(id);
	}
}
