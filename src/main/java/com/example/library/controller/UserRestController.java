package com.example.library.controller;


import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUseById/{id}")
    public Optional<User> getBookById(@PathVariable(value = "id") Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/updateUser/{id}")
    public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User userDetails) {
        Optional<User> user = userRepository.findById(id);
        User user_new = user.get();
        user_new.setFirstName(userDetails.getFirstName());
        user_new.setLastName(userDetails.getLastName());
        user_new.setPersonalIdNumber(userDetails.getPersonalIdNumber());
        user_new.addListBooks(userDetails.getList());

        return userRepository.save(user_new);

    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        Optional<User> user = userRepository.findById(id);
        User user_new = user.get();
        userRepository.delete(user_new);
    }

}
