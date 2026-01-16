package com.todoapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.entity.Task;
import com.todoapp.entity.User;
import com.todoapp.exception.ResourceNotFoundException;
import com.todoapp.exception.UnauthorizedException;
import com.todoapp.repository.TaskRepository;
import com.todoapp.repository.UserRepository;
import com.todoapp.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Task createTask(Task task, String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByUsername(String username) {
        return taskRepository.findByUserUsername(username);
    }

    @Override
    public Task updateTask(Long taskId, Task task, String username) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        
        if(!existingTask.getUser().getUsername().equals(username)){
            throw new UnauthorizedException("You are not allowed to update this task");
        }

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        existingTask.setDeadline(task.getDeadline());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long taskId, String username) {
        Task existingTask = taskRepository.findById(taskId)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        if(!existingTask.getUser().getUsername().equals(username)){
            throw new UnauthorizedException("You are not allowed to delete this task");
        }

        taskRepository.delete(existingTask);
    }
}
