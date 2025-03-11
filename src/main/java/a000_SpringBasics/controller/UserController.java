package a000_SpringBasics.controller;

import a000_SpringBasics.service.RedisService;
import a000_SpringBasics.dao.User;
import a000_SpringBasics.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chenyu Liu
 * @since 3/10/25 Monday
 **/

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    int createUser(@RequestBody User.Dto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/redis")
    String redisInfo(@RequestParam("key") String key) {
        return redisService.getKey(key);
    }

    @PostMapping("/redis")
    String redisInfo(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisService.setKey(key, value);
        return "a";
    }
}
