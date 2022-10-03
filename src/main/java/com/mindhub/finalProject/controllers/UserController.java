package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.UserDTO;
import com.mindhub.finalProject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/current")
    public UserDTO getAuthentication(Authentication authentication){
        return new UserDTO(userService.findUserByEmail(authentication.getName()));
    }

}
