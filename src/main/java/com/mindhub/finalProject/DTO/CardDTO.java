package com.mindhub.finalProject.DTO;

import com.mindhub.finalProject.models.Card;

import java.time.LocalDate;

public class CardDTO {

    private long id;
    private String cardHolder, cardNumber;
    private int cvv;
    private LocalDate thruDate;

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.id = card.getId();
        this.cardHolder = card.getCardHolder();
        this.cardNumber = card.getCardNumber();
        this.cvv = card.getCvv();
        this.thruDate = card.getThruDate();
    }

    /*GETTERS*/

    public long getId() {
        return id;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getCvv() {
        return cvv;
    }

    public LocalDate getThruDate() {
        return thruDate;
    }
}
