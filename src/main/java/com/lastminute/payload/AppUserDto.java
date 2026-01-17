package com.lastminute.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppUserDto {

    private Long id ;
    private String username;
    private String email;
    private String name;
    private String password;

}
