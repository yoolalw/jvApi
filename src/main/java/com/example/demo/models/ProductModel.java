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

    public String getNomeKimono(){
        return nomeKimono;
    }
    public void setNomeKimono(String nomeKimono){
        this.nomeKimono = nomeKimono;
    }

    public Double getPrecoKimono(){
        return precoKimono;
    }
    public void setPrecoKimono(Double precoKimono){
        this.precoKimono = precoKimono;
    }

    public Integer getQuantidadeKimono(){
        return quantidadeKimono;
    }
    public void setQuantidadeKimono(Integer quantidadeKimono){
        this.quantidadeKimono = quantidadeKimono;
    }

    public String getImagem(){
        return imagem;
    }

    public void setImagem(String imagem){
        this.imagem = imagem;
    }


}
