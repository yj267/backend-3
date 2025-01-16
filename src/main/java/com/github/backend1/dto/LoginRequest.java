package com.github.backend1.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
}
