package com.team3.dumb;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	@Autowired
	private TaskRepository taskRepository;

	public ArrayList<Task> getAllTask() {
		return (ArrayList<Task>) taskRepository.findAll();
	}

	public Optional<Task> getById(Integer id) {
		return taskRepository.findById(id);
	}

	public void addOrEditTask(Task task) {
		taskRepository.save(task);

	}

	public void deleteTask(Integer id) {
		taskRepository.deleteById(id);
	}
}
