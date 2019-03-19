package com.example.CRUD;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class TaskBean {
	
	@Autowired
	private TaskService taskService;
	
	private Task curTask;
	
	private List<Task> tasks;
	
	@PostConstruct
	private void init() {
		curTask = new Task();
		curTask.setName("");
		curTask.setCompleted(false);
		tasks = taskService.getTaskList();
	}
	
	public String save() throws IOException {
		if (curTask.getName() != null && !curTask.getName().isEmpty()) {
			curTask.setCompleted(false);
			try {
				taskService.save(curTask);
			}
			catch (ConstraintViolationException cve) {
				FacesContext.getCurrentInstance().addMessage("task-form:taskName", new FacesMessage("this task name is invalid"));
				cve.printStackTrace();
			}
		}
		curTask = new Task();
		updateList();
		return "/index.jsf?faces-redirect=true";
	}
	
	public void updateList() throws IOException {
		tasks = taskService.getTaskList();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/index.jsf");
	}

	public Task getCurTask() {
		return curTask;
	}

	public void setCurTask(Task curTask) {
		this.curTask = curTask;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	
}
