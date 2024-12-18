package com.sooraj.BlogApplication.services.impl;

import com.sooraj.BlogApplication.entities.User;
import com.sooraj.BlogApplication.exceptions.ResourceNotFoundException;
import com.sooraj.BlogApplication.payloads.UserDTO;
import com.sooraj.BlogApplication.repositories.UserRepository;
import com.sooraj.BlogApplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO user) {
        User savedUser = userRepository.save(modelMapper.map(user,User.class));
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO user, Integer id) {
        User getUser= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        return modelMapper.map(userRepository.save(modelMapper.map(user,User.class)), UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> allUsers= userRepository.findAll().stream().map(u -> modelMapper.map(u, UserDTO.class)).toList();
        return allUsers;
    }

    @Override
    public String deleteUser(Integer id) {
        User user= userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
        userRepository.delete(user);
        return "User deleted succesfully";
    }


}