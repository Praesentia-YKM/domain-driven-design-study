package com.domainPractice.drinkOrderDomain.domain.drink;

import com.domainPractice.drinkOrderDomain.common.exceptions.OrderException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Drink {

    @EmbeddedId
    private DrinkId id;

    private String name;

    @Enumerated(EnumType.STRING)
    private DrinkSize size;

    private BigDecimal price;

    private int stockQuantity; // 재고 수량

    private Drink(DrinkId id, String name, DrinkSize size, BigDecimal basePrice, int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("재고 수량은 0 이상이어야 합니다.");
        }

        this.id = id;
        this.name = name;
        this.size = size;
        this.price = size.adjustPrice(basePrice);
        this.stockQuantity = stockQuantity;
    }

    public static Drink of(DrinkId id, String name, DrinkSize size, BigDecimal basePrice, int stockQuantity) {
        return new Drink(id, name, size, basePrice, stockQuantity);
    }

    /**
     * 재고 확인 및 차감
     */
    public void validateAndReduceStock(int requiredQuantity) {
        if (nonSufficientStock(requiredQuantity)) {
            throw new OrderException("재고 부족: " + this.name);
        }

        this.stockQuantity -= requiredQuantity;
    }

    private boolean nonSufficientStock(int requiredQuantity) {
        return this.stockQuantity < requiredQuantity;
    }
}
