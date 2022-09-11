package com.bookshopping.service.impl;

import com.bookshopping.model.Cart;
import com.bookshopping.model.Role;
import com.bookshopping.model.User;
import com.bookshopping.repository.UserRepository;
import com.bookshopping.service.CartService;
import com.bookshopping.service.RoleService;
import com.bookshopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CartService cartService;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User saveCreateRelationship(User user) {
        Role role = roleService.findByName("ROLE_USER");
        if(role != null) {
            List<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
        }

        User user1 = userRepository.save(user);
        Cart cart = new Cart();
        cart.setUser(user1);
        cartService.save(cart);
        return user1;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
