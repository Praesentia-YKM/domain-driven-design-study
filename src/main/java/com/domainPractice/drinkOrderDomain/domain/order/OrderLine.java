package com.domainPractice.drinkOrderDomain.domain.order;

import com.domainPractice.drinkOrderDomain.common.exceptions.OrderException;
import com.domainPractice.drinkOrderDomain.domain.drink.Drink;
import com.domainPractice.drinkOrderDomain.domain.drink.DrinkRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO ManyToOne 의 fetch전략은 LAZY -> N+1이 생길 수 있지만 다양한 해결책은 존재한다.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Order order;

    // TODO ManyToOne 의 fetch전략은 LAZY -> N+1이 생길 수 있지만 다양한 해결책은 존재한다.
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Drink drink;

    private int quantity;

    private BigDecimal unitPrice;

    public OrderLine(Order order, Drink drink, int quantity) {
        this.order = order;
        this.drink = drink;
        this.quantity = quantity;
        this.unitPrice = drink.getPrice();
    }

    // TODO OrderLine에대한 validator 클래스를 분리해서 책임을 나누는게 어땠으라?
    // TODO 조회 쿼리에 대해서도 트랜잭션 관리가 성능상 필요함?
    public void performStockValidation(DrinkRepository drinkRepository) {
        Drink drinkFromDb = drinkRepository.findDrinkById(drink.getId())
                .orElseThrow(() -> new OrderException("음료 정보가 없습니다.: " + drink.getId()));

        drinkFromDb.validateAndReduceStock(this.quantity);
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}

