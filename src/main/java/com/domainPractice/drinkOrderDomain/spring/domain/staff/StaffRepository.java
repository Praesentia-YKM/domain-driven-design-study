package com.domainPractice.drinkOrderDomain.spring.domain.staff;

import java.util.Optional;

public interface StaffRepository {
    Optional<Staff> findStaffById(Long id);
}