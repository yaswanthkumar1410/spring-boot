package com.yash.notifier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.yash.notifier.dto.TaskRequest;
import com.yash.notifier.dto.TaskResponse;
import com.yash.notifier.exception.TaskNotFoundException;
import com.yash.notifier.model.Task;
import com.yash.notifier.model.User;
import com.yash.notifier.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(@Qualifier("taskRepository") TaskRepository repository) {
        taskRepository = repository;
    }

    public List<TaskResponse> getTasks() {
        List<Task> taskList = taskRepository.findAll();
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList) {
            TaskResponse taskResponse = new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
            taskResponseList.add(taskResponse);
        }
        return taskResponseList;
    }

    @Transactional
    public TaskResponse addTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setCompleted(taskRequest.isCompleted());
        task.setTitle(taskRequest.getTitle());
        User user = new User();
        user.setId(taskRequest.getUserId());
        task.setUser(user);
        task = taskRepository.save(task);
        TaskResponse taskResponse = new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
        return taskResponse;
    }

    public TaskResponse getTaskById(long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("No Task Found"));
        return new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
    }

    @Transactional
    public TaskResponse updateTaskById(long id, TaskRequest task) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("No Task Found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setCompleted(task.isCompleted());
        taskRepository.save(existingTask);
        return getTaskById(id);

    }

    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskResponse> getTasksByCompleted(boolean completed) {
        List<Task> taskList = taskRepository.findByCompleted(completed);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList) {
            TaskResponse taskResponse = new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
            taskResponseList.add(taskResponse);
        }
        return taskResponseList;
    }

    public List<TaskResponse> searchByTitle(String keyword) {
        List<Task> taskList = taskRepository.searchByTitle(keyword);
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskList) {
            TaskResponse taskResponse = new TaskResponse(task.isCompleted(), task.getId(), task.getTitle());
            taskResponseList.add(taskResponse);
        }
        return taskResponseList;
    }

    public Page<TaskResponse> getTasksPaged(int page, int size) {
        Page<Task> taskPage = taskRepository.findAll(PageRequest.of(page, size));
        List<TaskResponse> taskResponseList = new ArrayList<>();
        for (Task task : taskPage.getContent()) {
            taskResponseList.add(new TaskResponse(task.isCompleted(), task.getId(), task.getTitle()));
        }
        return new PageImpl<>(taskResponseList, taskPage.getPageable(), taskPage.getTotalElements());
    }

}
