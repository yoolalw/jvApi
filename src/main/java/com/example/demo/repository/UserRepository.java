
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{ //toda a configuracao vinda do banco de dados, adicionais, retiradas e verificacoes
    Optional<UserModel> findByEmailUser(String emailUser); //optional é um metodo de reconhecimento pelo http
    Boolean existsByEmailUser(String emailUser);
}
