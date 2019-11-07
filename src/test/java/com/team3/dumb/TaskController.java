package com.team3.dumb;

import java.util.ArrayList;
import java.util.Optional;

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

import com.team3.model.Record;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@GetMapping("tasks")
	public ArrayList<Task> getAllTask() {
		return taskService.getAllTask();
	}

	@GetMapping("tasks/{id}")
	public Optional<Task> getById(@PathVariable int id) {
		return taskService.getById(id);
	}

	@PostMapping("/tasks")
	public void addTask(@RequestBody Task task) {
		taskService.addOrEditTask(task);
	}

	@PutMapping("/tasks/{id}")
	public void editTask(@RequestBody Task task) {
		taskService.addOrEditTask(task);
	}

	@DeleteMapping("/tasks/{id}")
	public void deleteTask(@PathVariable Integer id) {
		taskService.deleteTask(id);
	}

}
