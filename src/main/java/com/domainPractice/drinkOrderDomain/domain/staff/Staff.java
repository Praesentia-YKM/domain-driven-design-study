package com.domainPractice.drinkOrderDomain.domain.staff;

import com.domainPractice.drinkOrderDomain.domain.order.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 직원 ID

    private String name; // 직원 이름

    @Enumerated(EnumType.STRING)
    private Role role; // 직원 역할 (BARISTA, CASHIER)

    public Staff(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public boolean canStaffChangeStatus(Role role, OrderStatus currentStatus, OrderStatus newStatus) {
        if (!STATUS_TRANSITIONS.containsKey(role)) {
            return false;
        }

        return STATUS_TRANSITIONS.get(role)
                                 .getOrDefault(currentStatus, null)
                                 == newStatus;
    }

    private static final Map<Role, Map<OrderStatus, OrderStatus>> STATUS_TRANSITIONS = Map.of(
        Role.CASHIER, Map.of(OrderStatus.PENDING, OrderStatus.IN_PROGRESS),
        Role.BARISTA, Map.of(OrderStatus.IN_PROGRESS, OrderStatus.READY)
    );
}