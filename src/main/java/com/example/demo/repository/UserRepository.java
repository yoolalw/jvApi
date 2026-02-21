
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.UserModel;
import java.util.List;



public interface UserRepository extends JpaRepository<UserModel, Integer>{
    Optional<UserModel> findByEmailUser(String emailUser); //optional é um metodo de reconhecimento pelo http
    Boolean existsByEmailUser(String emailUser);
}
