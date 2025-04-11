package com.example.infoevenement.controller;

import com.example.infoevenement.dao.QEvenement;
import com.querydsl.core.types.dsl.BooleanExpression;

public class EvenementPredicateBuilder {
    public BooleanExpression build(String libelleContain, String lieux, String category, String periode) {
        BooleanExpression predicate = QEvenement.evenement.isNotNull();

        if (libelleContain != null && !libelleContain.isEmpty()) {
            predicate = predicate.and(QEvenement.evenement.libelle.containsIgnoreCase(libelleContain));
        }
        if (lieux != null && !lieux.isEmpty()) {
            predicate = predicate.and(QEvenement.evenement.lieux.id.eq(lieux));
        }
        if (category != null && !category.isEmpty()) {
            predicate = predicate.and(QEvenement.evenement.category.id.eq(category));
        }
        if (periode != null && !periode.isEmpty()) {
            predicate = predicate.and(QEvenement.evenement.periode.id.eq(periode));
        }

        return predicate;
    }
}