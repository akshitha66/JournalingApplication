package com.akshitha.journalApp.Controller;

import com.akshitha.journalApp.Service.UserService;
import com.akshitha.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-users")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
}
