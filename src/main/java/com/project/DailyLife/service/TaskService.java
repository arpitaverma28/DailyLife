package com.project.DailyLife.service;

import com.project.DailyLife.entity.Task;
import com.project.DailyLife.entity.User;
import com.project.DailyLife.repository.TaskRepository;
import com.project.DailyLife.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTask() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username);

        return taskRepository.findByUser(user);
    }

    public void createTask(String title){

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username);

        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        task.setUser(user); // ⭐ MOST IMPORTANT

        taskRepository.save(task);
    }
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
    public void toggleTask(Long id){
        Task task= taskRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid task id"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
