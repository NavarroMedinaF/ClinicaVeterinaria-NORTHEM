package com.mindhub.finalProject.Services.Implements;


import com.mindhub.finalProject.Services.CardService;
import com.mindhub.finalProject.models.Card;
import com.mindhub.finalProject.Services.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;

    @Override
    public List<Card> findCards() {
        return cardRepository.findAll();
    }
    @Override
    public Card findCardById(Long id){
        return cardRepository.findById(id).orElse(null);
    }
    @Override
    public void saveCard(Card card){
        cardRepository.save(card);
    }
    @Override
    public void deleteCard(Card card){
        cardRepository.delete(card);
    }
}
