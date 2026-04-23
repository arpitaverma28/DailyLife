package com.project.DailyLife.service;

import com.project.DailyLife.entity.User;
import com.project.DailyLife.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    public void save(User user){
        userRepository.save(user);
    }
}
