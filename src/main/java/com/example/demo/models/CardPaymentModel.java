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

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getDadosCartao(){
        return dadosCartao;
    }
    public void setDadosCartao(String dadosCartao){
        this.dadosCartao = dadosCartao;
    }

    public String getValidade(){
        return validade;
    }
    public void setValidade(String validade){
        this.validade = validade;
    }

    public String getCvc(){
        return cvc;
    }
    public void setCvc(String cvc){
        this.cvc = cvc;
    }

    public String getNomeCartao(){
        return nomeCartao;
    }
    public void setNomeCartao(String nomeCartao){
        this.nomeCartao = nomeCartao;
    }

    public String getEndereco(){
        return endereco;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getTipoCartao(){
        return tipoCartao;
    }
    public void setTipoCartao(String tipoCartao){
        this.tipoCartao = tipoCartao;
    }


}
