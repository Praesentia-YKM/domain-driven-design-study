package com.domainPractice.drinkOrderDomain.spring.infrastructure.persistence.staff;

import com.domainPractice.drinkOrderDomain.spring.domain.staff.Staff;
import com.domainPractice.drinkOrderDomain.spring.domain.staff.StaffRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStaffRepository extends JpaRepository<Staff, Long>, StaffRepository {
}
