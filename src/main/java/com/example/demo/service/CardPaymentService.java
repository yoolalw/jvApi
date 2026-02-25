package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.models.CardPaymentModel;
import com.example.demo.repository.CardPaymentRepository;


@Service
public class CardPaymentService {
    private CardPaymentRepository cardPaymentRepository;

    public CardPaymentService(CardPaymentRepository cardPaymentRepository){
        this.cardPaymentRepository = cardPaymentRepository;
    }

    public List<CardPaymentModel> listarCartao(){
        Sort sort = Sort.by("email").descending()
        .and(Sort.by("dadosCartao")).ascending()
        .and(Sort.by("validade")).ascending()
        .and(Sort.by("cvc")).ascending()
        .and(Sort.by("nomeCartao")).descending();

        return cardPaymentRepository.findAll(sort);
    }

    // @PostMapping
    public CardPaymentModel cadastrarCartao(CardPaymentModel cardPaymentModel) {
        return cardPaymentRepository.save(cardPaymentModel);
    }

    // @GetMapping
    public CardPaymentModel buscarPorId(Integer id) {
        return cardPaymentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pagamento com ID " + id + " não encontrado."));
    }

    // @PutMapping
    public CardPaymentModel atualizarCartao(Integer id, CardPaymentModel novoCartao) {
        CardPaymentModel existente = buscarPorId(id);
        existente.setEmail(novoCartao.getEmail());
        existente.setDadosCartao(novoCartao.getDadosCartao());
        existente.setValidade(novoCartao.getValidade());
        existente.setCvc(novoCartao.getCvc());
        existente.setNomeCartao(novoCartao.getNomeCartao());

        return cardPaymentRepository.save(existente);
    }


    // @DeleteMapping
    public void deletarCartao(Integer id) {
        if (!cardPaymentRepository.existsById(id)) {
            throw new NoSuchElementException("Pagamento com ID " + id + " não encontrado.");
        }
        cardPaymentRepository.deleteById(id);
    }

    



}
