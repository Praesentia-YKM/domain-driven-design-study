package com.domainPractice.drinkOrderDomain.infrastructure.persistence.staff;

import com.domainPractice.drinkOrderDomain.domain.staff.Staff;
import com.domainPractice.drinkOrderDomain.domain.staff.StaffRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStaffRepository extends JpaRepository<Staff, Long>, StaffRepository {
}
