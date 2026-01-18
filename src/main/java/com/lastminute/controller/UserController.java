package com.lastminute.controller;

import com.lastminute.payload.APIResponse;
import com.lastminute.payload.AppUserDto;
import com.lastminute.payload.LoginDto;
import com.lastminute.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
// 10/10/2024
    private UserService userService;

    private AuthenticationManager authenticationManager;

    public UserController(UserService userService, AuthenticationManager authenticationManager) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // http://localhost:8080/api/v1/users
    @PostMapping("/signup")
    public ResponseEntity<APIResponse<String>> signupUser(@RequestBody AppUserDto appUserDto){
        APIResponse<String> response = userService.signup(appUserDto);
        return new ResponseEntity<>(response , HttpStatusCode.valueOf(response.getStatus()));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody LoginDto loginDto){

        APIResponse<String> response = new APIResponse<>();

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),loginDto.getPassword());

        try{
            Authentication authenticate = authenticationManager.authenticate(token);
            if(authenticate.isAuthenticated()){
                response.setMessage("Login Successful");
                response.setStatus(200);
                response.setData("User has logged");
                return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
               }
        }catch(Exception e){
            e.printStackTrace();
        }
        response.setMessage("Login Failed");
        response.setStatus(401);
        response.setData("Un-Authorized access");
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatus()));
    }
}
