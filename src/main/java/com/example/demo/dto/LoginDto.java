package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {
    private String emailUser;
    private String senhaUser;

    public String getEmailUser() { return emailUser; }
    public void setEmailUser(String emailUser) { this.emailUser = emailUser; }

    public String getSenhaUser() { return senhaUser; }
    public void setSenhaUser(String senhaUser) { this.senhaUser = senhaUser; }
}
