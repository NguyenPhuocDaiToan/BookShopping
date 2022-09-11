package com.bookshopping.service;

import com.bookshopping.model.User;

public interface UserService {
    User save(User user);
    User saveCreateRelationship(User user);
    User findByEmail(String email);
    User findById(Integer id);
    Boolean existsByEmail(String email);
}
