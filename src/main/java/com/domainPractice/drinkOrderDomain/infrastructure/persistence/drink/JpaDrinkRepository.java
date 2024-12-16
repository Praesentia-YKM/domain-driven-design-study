package com.domainPractice.drinkOrderDomain.infrastructure.persistence.drink;

import com.domainPractice.drinkOrderDomain.domain.drink.Drink;
import com.domainPractice.drinkOrderDomain.domain.drink.DrinkRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDrinkRepository extends JpaRepository<Drink, Long>, DrinkRepository {
}
