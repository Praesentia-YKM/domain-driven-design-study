package com.domainPractice.drinkOrderDomain.domain.drink;

import java.util.Optional;

public interface DrinkRepository {
    Optional<Drink> findDrinkById(DrinkId id);
}