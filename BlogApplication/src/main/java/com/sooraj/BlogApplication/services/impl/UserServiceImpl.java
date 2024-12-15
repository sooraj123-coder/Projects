package com.sooraj.BlogApplication.services.impl;

import com.sooraj.BlogApplication.entities.User;
import com.sooraj.BlogApplication.payloads.UserDTO;
import com.sooraj.BlogApplication.repositories.UserRepository;
import com.sooraj.BlogApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO user) {

        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer id) {
        return null;
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user= userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not exists"));
        return null;//new UserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

       // return  userRepository.findAll().stream().map(UserDTO :: new).collect(Collectors.toList());
    return null;
    }

    @Override
    public String deleteUser(Integer id) {
        User user= userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not exists"));
        userRepository.delete(user);
        return "User deleted succesfully";
    }
}
