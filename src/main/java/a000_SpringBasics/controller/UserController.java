package a000_SpringBasics.controller;

import a000_SpringBasics.dao.User;
import a000_SpringBasics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chenyu Liu
 * @since 3/10/25 Monday
 **/

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    int createUser(@RequestBody User.Dto userDto){
        return userService.createUser(userDto);
    }
}
