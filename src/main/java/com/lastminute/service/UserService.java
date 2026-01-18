package com.lastminute.service;

import com.lastminute.entity.AppUser;
import com.lastminute.payload.APIResponse;
import com.lastminute.payload.AppUserDto;
import com.lastminute.repository.AppUserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private AppUserRepository appUserRepository;

    public UserService(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;
    }

    public APIResponse<String> signup(AppUserDto appUserDto){

        APIResponse<String> response = new APIResponse<>();

        if(appUserRepository.existsByUsername(appUserDto.getUsername())){
            response.setMessage("Registration Failed");
            response.setStatus(400);
            response.setData("Username already exists");
            return response;
        }

        if(appUserRepository.existsByEmail(appUserDto.getEmail())){
            response.setMessage("Registration Failed");
            response.setStatus(400);
            response.setData("Email already exists");
            return response;
        }

        AppUser appUser = new AppUser();
        BeanUtils.copyProperties(appUserDto , appUser);
        appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        AppUser savedUser = appUserRepository.save(appUser) ;

        if (savedUser == null) {
            response.setMessage("Registration Failed");
            response.setStatus(500);
            response.setData("Unexpected server error");
            return response;
        }

        response.setMessage("Registration Successful");
        response.setStatus(201);
        response.setData("User Registered Successfully");
        return response ;
    }

}
