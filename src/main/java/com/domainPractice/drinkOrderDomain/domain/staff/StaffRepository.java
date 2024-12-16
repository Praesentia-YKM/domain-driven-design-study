package com.domainPractice.drinkOrderDomain.domain.staff;

import java.util.Optional;

public interface StaffRepository {
    Optional<Staff> findStaffById(Long id);
}