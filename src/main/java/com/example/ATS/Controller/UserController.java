package com.example.ATS.Controller;

import com.example.ATS.DTO.LoginRequest;
import com.example.ATS.DTO.RegisterUser;
import com.example.ATS.Model.Users;
import com.example.ATS.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public String createUser(@RequestBody RegisterUser registerUser){
        return userService.createUser(registerUser);
    }


}
