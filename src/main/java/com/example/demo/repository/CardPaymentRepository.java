
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.CardPaymentModel;


public interface CardPaymentRepository extends JpaRepository<CardPaymentModel, Integer>{
    Optional<CardPaymentModel> findByEmailUser(String emailUser);
}
