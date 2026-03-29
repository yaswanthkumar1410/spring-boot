package com.yash.notifier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yash.notifier.dto.UserRequest;
import com.yash.notifier.dto.UserResponse;
import com.yash.notifier.exception.ResourceNotFoundException;
import com.yash.notifier.model.User;
import com.yash.notifier.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(@Qualifier("userRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        userRepository.save(user);
    }

    public List<UserResponse> getAllUsers() {
        List<User> userlist = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for(User user : userlist) {
            userResponseList.add(new UserResponse(user.getId(), user.getName(), user.getEmail()));
        }
        return userResponseList;
    }

    public UserResponse getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No User Found"));
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}
