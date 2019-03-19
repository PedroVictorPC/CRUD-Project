package com.example.CRUD;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void save(Task t) {
		taskRepository.save(t);
	}
	
	public List<Task> getTaskList() {
		return taskRepository.findAll();
	}
	
}
