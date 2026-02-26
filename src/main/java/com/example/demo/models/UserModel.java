package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserModel {
    @Id
    @GeneratedValue
    private Integer id;
    private String nomeUser;
    private String senhaUser;
    private String emailUser;

    public String getNomeUser(){
        return nomeUser;
    }
    public void setNomeUser(String nomeUser){
        this.nomeUser = nomeUser;
    }

    public String getSenhaUser(){
        return senhaUser;
    }

    public void setSenhaUser(String senhaUser){
        this.senhaUser = senhaUser;

    }

    public String getEmailUser(){
        return emailUser;
    }
    public void setEmailUser(String emailUser){
        this.emailUser = emailUser;
    }
}
