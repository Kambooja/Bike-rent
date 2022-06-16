package com.sha.serverbikemanagement.service;

import com.sha.serverbikemanagement.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);

    User findByUsername(String username);

    List<User> findAllUsers();

    Long numberOfUsers();
}
