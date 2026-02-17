package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
@Entity
@Table(name = "pagamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CardPaymentModel {
    @Id
    @GeneratedValue
    private Integer Id;
    private String email;
    private Integer dadosCartao;
    private String validade;
    private Integer cvc;
    private String nomeCartao;
}
