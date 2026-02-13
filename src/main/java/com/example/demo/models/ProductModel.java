package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
@Entity
@Table(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductModel {
    @Id
    @GeneratedValue
    private Integer id;
    private String nomeKimono;
    private Double precoKimono;
    private Integer quantidadeKimono;
    private String imagem;
}
