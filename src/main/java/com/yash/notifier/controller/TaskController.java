package com.yash.notifier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yash.notifier.dto.TaskRequest;
import com.yash.notifier.dto.TaskResponse;
import com.yash.notifier.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getTasks() {
        return taskService.getTasks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse addTask(@Valid @RequestBody TaskRequest task) {
        return taskService.addTask(task);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public void updateTaskById(@RequestBody TaskRequest task, @PathVariable long id) {
       taskService.updateTaskById(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTaskById(@PathVariable long id) {
        taskService.deleteTaskById(id);
    }

    @GetMapping("/search")
    public List<TaskResponse> searchCompletedTasks(@RequestParam boolean completed) {
        return taskService.getTasksByCompleted(completed);
    }

    @GetMapping("/info")
    public String getInfo() {
        return appName + " v" + appVersion;
    }
}
