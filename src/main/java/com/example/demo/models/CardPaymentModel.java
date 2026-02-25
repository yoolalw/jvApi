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
    private String dadosCartao;
    private String validade;
    private String cvc;
    private String nomeCartao;
    private String endereco;
    private String tipoCartao;

}
