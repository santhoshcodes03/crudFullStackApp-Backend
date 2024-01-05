package com.application.fullStackCrud.controller;

import com.application.fullStackCrud.exception.UserNotFoundException;
import com.application.fullStackCrud.model.User;
import com.application.fullStackCrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    //ADDING NEW USER TO DATABASE
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    //GETTING ALL USERS FROM DATABASE
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //GETTING SINGLE/SPECIFIC USER FROM THE DATABASE
    @GetMapping("/user/{id}")
    User getSpecificUser(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

    //UPDATING A SPECIFIC/SINGLE USER
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id).map((user)->{
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(()-> new UserNotFoundException(id));
    }

    //DELETING USER BY THEIR ID
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }else{
            userRepository.deleteById(id);
        }

        return "user deleted successfully";
    }


}
