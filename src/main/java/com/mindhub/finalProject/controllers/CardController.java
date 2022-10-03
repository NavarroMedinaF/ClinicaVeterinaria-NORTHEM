package com.mindhub.finalProject.controllers;

import com.mindhub.finalProject.DTO.CardDTO;
import com.mindhub.finalProject.Services.CardService;
import com.mindhub.finalProject.Services.UserService;
import com.mindhub.finalProject.models.Card;
import com.mindhub.finalProject.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    UserService userService;

    @GetMapping("/cards")
    public Set<CardDTO> getAllCards() {
        return cardService.findCards().stream().map(CardDTO::new).collect(Collectors.toSet());
    }

    @GetMapping("/cards/{id}")
    public CardDTO getCardById(@PathVariable Long id){
        return new CardDTO(cardService.findCardById(id));
    }

    @PostMapping("/cards")
    public ResponseEntity<Object> addCard(@RequestParam String cardHolder, @RequestParam String cardNumber,
                                          @RequestParam int cvv, @RequestParam LocalDate thruDate,
                                          Authentication authentication){

        Client authClient = userService.findClientByEmail(authentication.getName());
        if (authClient != null){
            if (cardHolder.isEmpty() || cardNumber.isEmpty() || (cvv > 999 || cvv < 100) || thruDate.isBefore(LocalDate.now())){
                return new ResponseEntity<>("Los datos no son correctos. Por favor, revisa los campos.", HttpStatus.FORBIDDEN);
            }
        }else{
            return new ResponseEntity<>("Su usuario no ha sido autenticado. Por favor, vuelva loguearse.", HttpStatus.FORBIDDEN);
        }

        Card newCard = new Card(authClient, cardHolder, cardNumber, cvv, thruDate);
        authClient.getCards().add(newCard);
        cardService.saveCard(newCard);

        return new ResponseEntity<>("Se ha agregado la tarjeta con exito.", HttpStatus.CREATED);

    }
    @DeleteMapping("/cards")
    public ResponseEntity<Object> deleteCard(@RequestParam Long idCard, Authentication authentication){

        Client authClient = userService.findClientByEmail(authentication.getName());
        Card selectedCard = cardService.findCardById(idCard);
        if (authClient != null){
            if (selectedCard == null){
                return new ResponseEntity<>("Por favor, seleccione una tarjeta que desee eliminar.", HttpStatus.FORBIDDEN);
            }
        }else {
            return new ResponseEntity<>("Su usuario no ha sido autenticado. Por favor, vuelva loguearse.", HttpStatus.FORBIDDEN);
        }

        /*authClient.getCards().remove(selectedCard);*/
        cardService.deleteCard(selectedCard);

        return new ResponseEntity<>("Se ha eliminado la tarjeta de tu método de pago.", HttpStatus.OK);
    }
}
