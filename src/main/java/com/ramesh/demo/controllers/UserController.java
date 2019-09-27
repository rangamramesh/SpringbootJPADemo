package com.ramesh.demo.controllers;


import com.ramesh.demo.models.BasicResponse;
import com.ramesh.demo.models.User;
import com.ramesh.demo.repositories.UserJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserJPARepository userRepository;

    @GetMapping(value = "/all")
    private List<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{name}")
    private List<User> findByName(@PathVariable String name) {
        return userRepository.findByName(name);
    }

    @PostMapping(value = "/load")
    private User loadUser(@RequestBody User user) {
        userRepository.save(user);
        return userRepository.findByName(user.getName()).get(0);
    }

    @GetMapping(value = "/user/{id}")
    private User findUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        try {
            return user.get();
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/delete/{id}")
    private BasicResponse loadUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        User user = findUser(id);
        BasicResponse response = new BasicResponse();
        if (user != null) {
            response.setStatus("Fail");
            response.setMessage("Unable Remove the User, Please try after some time.");
        } else {
            response.setStatus("Success");
            response.setMessage("Successfully Removed the User from the Team");
        }

        return response;
    }


}
