package com.mindhub.finalProject.Services;

import com.mindhub.finalProject.models.Card;

import java.util.List;

public interface CardService {

    List<Card> findCards();

    Card findCardById(Long id);

    void saveCard(Card card);

    void deleteCard(Card card);
}
