
package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.models.CardPaymentModel;


public interface CardPaymentRepository extends JpaRepository<CardPaymentModel, Integer>{
}
