package com.yash.notifier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yash.notifier.dto.TaskRequest;
import com.yash.notifier.dto.TaskResponse;
import com.yash.notifier.exception.TaskNotFoundException;
import com.yash.notifier.model.Task;
import com.yash.notifier.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(@Qualifier("taskRepository") TaskRepository repository) {
        taskRepository = repository;
    }

    public List<TaskResponse> getTasks() {
        List<Task> taskList =  taskRepository.findAll();
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for(Task task : taskList) {
            TaskResponse taskResponse = new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
            taskResponseList.add(taskResponse);
        }
        return taskResponseList;
    }

    public TaskResponse addTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setCompleted(taskRequest.isCompleted());
        task.setTitle(taskRequest.getTitle());
        task = taskRepository.save(task);
        TaskResponse taskResponse = new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
        return taskResponse;
    }

    public TaskResponse getTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("No Task Found"));
        return new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
    }

    public void updateTaskById(long id, TaskRequest task) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("No Task Found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted(task.isCompleted());
        taskRepository.save(existingTask);
    }

    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskResponse> getTasksByCompleted(boolean completed) {
        List<Task> taskList =  taskRepository.findByCompleted(completed);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for(Task task : taskList) {
            TaskResponse taskResponse = new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
            taskResponseList.add(taskResponse);
        }
        return taskResponseList;
    }
}
