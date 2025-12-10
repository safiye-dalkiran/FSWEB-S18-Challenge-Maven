package com.workintech.fswebs18challengemaven.repository;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Color;
import com.workintech.fswebs18challengemaven.entity.Type;
import com.workintech.fswebs18challengemaven.exceptions.CardException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardRepositoryImpl implements CardRepository {

    private final EntityManager entityManager;

    @Autowired
    public CardRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Card save(Card card) {
        entityManager.persist(card);
        return card;
    }

    @Override
    public List<Card> findAll() {
        TypedQuery<Card> query =
                entityManager.createQuery("SELECT c FROM Card c", Card.class);

        return query.getResultList();
    }

    @Override
    public List<Card> findByColor(String color) {
        Color enumColor;
        try {
            enumColor = Color.valueOf(color.toUpperCase());
        } catch (Exception e) {
            throw new CardException("Invalid color: " + color, HttpStatus.BAD_REQUEST);
        }

        TypedQuery<Card> query =
                entityManager.createQuery("SELECT c FROM Card c WHERE c.color = :color", Card.class);

        query.setParameter("color", enumColor);

        List<Card> result = query.getResultList();

        if (result.isEmpty()) {
            throw new CardException("No cards found with color: " + color, HttpStatus.NOT_FOUND);
        }

        return result;
    }

    @Override
    public List<Card> findByValue(Integer value) {

        TypedQuery<Card> query =
                entityManager.createQuery("SELECT c FROM Card c WHERE c.value = :value", Card.class);

        query.setParameter("value", value);

        List<Card> result = query.getResultList();

        if (result.isEmpty()) {
            throw new CardException("No cards found with value: " + value, HttpStatus.NOT_FOUND);
        }

        return result;
    }

    @Override
    public List<Card> findByType(String type) {
        Type enumType;
        try {
            enumType = Type.valueOf(type.toUpperCase());
        } catch (Exception e) {
            throw new CardException("Invalid type: " + type, HttpStatus.BAD_REQUEST);
        }

        TypedQuery<Card> query =
                entityManager.createQuery("SELECT c FROM Card c WHERE c.type = :type", Card.class);

        query.setParameter("type", enumType);

        List<Card> result = query.getResultList();

        if (result.isEmpty()) {
            throw new CardException("No cards found with type: " + type, HttpStatus.NOT_FOUND);
        }

        return result;
    }

    @Override
    public Card update(Card card) {
        return entityManager.merge(card);
    }

    @Override
    public Card remove(Long id) {
        Card found = entityManager.find(Card.class, id);

        if (found == null) {
            throw new CardException("Card not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        entityManager.remove(found);
        return found;
    }
}