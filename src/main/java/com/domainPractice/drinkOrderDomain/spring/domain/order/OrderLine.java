package com.domainPractice.drinkOrderDomain.spring.domain.order;

import com.domainPractice.drinkOrderDomain.spring.common.exceptions.OrderException;
import com.domainPractice.drinkOrderDomain.spring.domain.customer.CustomerId;
import com.domainPractice.drinkOrderDomain.spring.domain.drink.Drink;
import com.domainPractice.drinkOrderDomain.spring.domain.drink.DrinkRepository;
import jakarta.persistence.Entity;
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

    @ManyToOne(optional = false)
    private Order order;

    @ManyToOne(optional = false)
    private Drink drink;

    private int quantity;

    private BigDecimal unitPrice;

    // TODO 한 주문에서 여러 사용자가 음료를 주문할 경우는 없으니까 주문자(customerId)는 알 필요가 없다?
    public OrderLine(Order order, Drink drink, int quantity) {
        this.order = order;
        this.drink = drink;
        this.quantity = quantity;
        this.unitPrice = drink.getPrice();
    }

    // TODO 주문할 음료의 재고 확인은 주문선의 역할이 아닐까?
    //  자신이 참조하는 Drink 객체의 재고를 확인하고, 필요시 재고를 차감
    public void performStockValidation(DrinkRepository drinkRepository) {
        Drink drinkFromDb = drinkRepository.findDrinkById(drink.getId())
                .orElseThrow(() -> new OrderException("음료 정보가 없습니다.: " + drink.getId()));

        drinkFromDb.validateAndReduceStock(this.quantity);
    }

    public BigDecimal getTotalPrice() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}

