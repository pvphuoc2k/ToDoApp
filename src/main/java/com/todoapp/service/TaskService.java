package com.todoapp.service;

import java.util.List;

import com.todoapp.entity.Task;

public interface TaskService {
    Task createTask(Task task, String username);
    List<Task> getTasksByUsername(String username);
    Task updateTask(Long taskId, Task task, String username);
    void deleteTask(Long taskId, String username);
}
