package com.sooraj.BlogApplication.controllers;

import com.sooraj.BlogApplication.entities.User;
import com.sooraj.BlogApplication.payloads.UserDTO;
import com.sooraj.BlogApplication.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //save user
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userdto){
        return new ResponseEntity<>(userService.createUser(userdto), HttpStatus.CREATED);
    }

    //get all users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    //get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.FOUND);
    }

    //update user
    @PutMapping("update/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable int id){
        return new ResponseEntity<>(userService.updateUser(userDTO,id), HttpStatus.CREATED);
    }

    //delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<UserDTO> partiallyUpdateRecord(@RequestBody Map<String,Object> partialUpdate, @PathVariable Integer id){

        UserDTO userDTO= userService.getUserById(id);
        partialUpdate.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(UserDTO.class,key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, userDTO, value);
        });

        return new ResponseEntity<>(userService.updateUser(userDTO,id), HttpStatus.OK);
    }
}