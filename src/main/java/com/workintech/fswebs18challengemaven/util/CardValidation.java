package com.workintech.fswebs18challengemaven.util;


import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.entity.Type;

public class CardValidation {

    public static void validateCard(Card card) {
        if (card.getType() != null && card.getValue() != null) {
            throw new IllegalArgumentException("Card cannot have both type and value.");
        }

        if (card.getType() != null && card.getType() == Type.JOKER) {
            if (card.getValue() != null || card.getColor() != null) {
                throw new IllegalArgumentException("JOKER card must have null value and color.");
            }
        }

        if (card.getType() == null && card.getValue() == null) {
            throw new IllegalArgumentException("Card must have either a type or a value.");
        }
    }
}
