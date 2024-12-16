package com.domainPractice.drinkOrderDomain.spring.infrastructure.persistence.drink;

import com.domainPractice.drinkOrderDomain.spring.domain.customer.Customer;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.spring.domain.drink.Drink;
import com.domainPractice.drinkOrderDomain.spring.domain.drink.DrinkId;
import com.domainPractice.drinkOrderDomain.spring.domain.drink.DrinkRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaDrinkRepository extends JpaRepository<Drink, Long>, DrinkRepository {
}
