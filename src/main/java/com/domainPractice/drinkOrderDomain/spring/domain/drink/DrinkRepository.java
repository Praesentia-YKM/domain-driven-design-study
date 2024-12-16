package com.domainPractice.drinkOrderDomain.spring.domain.drink;

import java.util.Optional;

public interface DrinkRepository {
    Optional<Drink> findDrinkById(DrinkId id);
}