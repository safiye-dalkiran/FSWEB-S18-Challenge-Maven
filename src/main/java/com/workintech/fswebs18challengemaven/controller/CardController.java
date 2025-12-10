package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.repository.CardRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cards")
public class CardController {
    private CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> getAll() {
        return cardRepository.findAll();
    }

    @GetMapping("/byValue/{value}")
    public List<Card> getByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }
    @GetMapping("/byType/{type}")
    public List<Card> getByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }
    @GetMapping("/byColor/{color}")
    public List<Card> getByColor(@PathVariable String color) {
        return cardRepository.findByColor(color);
    }

    @PostMapping
    public Card saveCard(@RequestBody Card card) {
        cardRepository.save(card);
        return card;
    }

    @PutMapping("/")
    public Card switchCards(@RequestBody Card card) {
        cardRepository.update(card);
        return card;
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardRepository.remove(id);
    }
}