package org.example.service;

import org.example.domain.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    void insertUser();
}
