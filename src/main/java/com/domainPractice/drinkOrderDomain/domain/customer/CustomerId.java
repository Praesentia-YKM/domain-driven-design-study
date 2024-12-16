package com.domainPractice.drinkOrderDomain.domain.customer;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CustomerId implements Serializable {

    private Long value;
}
