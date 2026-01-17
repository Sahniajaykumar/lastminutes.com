package com.lastminute.controller;

import com.lastminute.payload.APIResponse;
import com.lastminute.payload.AppUserDto;
import com.lastminute.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
// 10/10/2024
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // http://localhost:8080/api/v1/users
    @PostMapping("/signup")
    public ResponseEntity<APIResponse<String>> signupUser(@RequestBody AppUserDto appUserDto){
        APIResponse<String> response = userService.signup(appUserDto);
        return new ResponseEntity<>(response , HttpStatusCode.valueOf(response.getStatus()));
    }
}
