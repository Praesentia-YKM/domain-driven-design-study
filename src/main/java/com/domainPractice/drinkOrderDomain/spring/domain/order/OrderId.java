package com.domainPractice.drinkOrderDomain.spring.domain.order;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class OrderId implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long value;

    public OrderId(Long value) {
        this.value = value;
    }

    // TODO db 자동채번해서 필요없음
    public static OrderId of(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("OrderId 값은 null이거나 0 이하일 수 없습니다.");
        }
        return new OrderId(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
