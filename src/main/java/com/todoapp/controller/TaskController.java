package com.todoapp.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.todoapp.entity.Task;
import com.todoapp.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task, Authentication authentication) {
        String username = authentication.getName();
        return taskService.createTask(task, username);
    }

    @GetMapping()
    public List<Task> getMyTasks(Authentication authentication) {
        String username = authentication.getName();
        return taskService.getTasksByUsername(username);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable("id") Long taskId,
                            @RequestBody Task task,
                            Authentication authentication) {
        String username = authentication.getName();
        return taskService.updateTask(taskId, task, username);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long taskId,
                            Authentication authentication) {
        String username = authentication.getName();
        taskService.deleteTask(taskId, username);
    }
}
