package com.lastminute.service;

import com.lastminute.entity.AppUser;
import com.lastminute.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository) {

        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), Collections.emptyList());
    }
}
