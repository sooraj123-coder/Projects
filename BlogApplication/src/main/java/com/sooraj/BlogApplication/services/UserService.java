package com.sooraj.BlogApplication.services;


import com.sooraj.BlogApplication.entities.User;
import com.sooraj.BlogApplication.payloads.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Integer id);
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();
    String deleteUser(Integer id);
}
