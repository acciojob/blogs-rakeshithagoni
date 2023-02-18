package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository3;

    @Autowired
    BlogService blogService3;

    public void createUser(User user){
        userRepository3.save(user);
    }

    public void deleteUser(int userId){
        if(userRepository3.findById(userId).isPresent())
        {
            userRepository3.deleteById(userId);
        }
    }

    public void updateUser(User user){
        User currentUser = userRepository3.findByUsername(user.getUsername());
        if (currentUser != null){
            currentUser.setUsername(user.getUsername());
            currentUser.setFirstName(user.getFirstName());
            currentUser.setLastName(user.getLastName());
            currentUser.setPassword(user.getPassword());

            userRepository3.save(currentUser);
        }
        userRepository3.save(user);
    }

    public User findUserByUsername(String username){
        return userRepository3.findByUsername(username);
    }
}