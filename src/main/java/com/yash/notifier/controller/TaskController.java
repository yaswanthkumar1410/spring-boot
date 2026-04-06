package com.yash.notifier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yash.notifier.dto.TaskRequest;
import com.yash.notifier.dto.TaskResponse;
import com.yash.notifier.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:3030")
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
    public ResponseEntity<List<TaskResponse>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @PostMapping
    public ResponseEntity<TaskResponse> addTask(@Valid @RequestBody TaskRequest task) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.addTask(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTaskById(@RequestBody TaskRequest task, @PathVariable long id) {
        TaskResponse taskResponse = taskService.updateTaskById(id, task);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchCompletedTasks(@RequestParam boolean completed) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTasksByCompleted(completed));
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok(appName + " v" + appVersion);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<TaskResponse>> searchByTitle(@RequestParam String keyword) {
        return ResponseEntity.ok(taskService.searchByTitle(keyword));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<TaskResponse>> getTasksPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(taskService.getTasksPaged(page, size));
    }

}
