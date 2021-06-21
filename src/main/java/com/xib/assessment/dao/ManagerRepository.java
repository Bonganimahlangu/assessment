package com.xib.assessment.dao;

import com.xib.assessment.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository  extends JpaRepository<Manager, Long> {
    boolean existsByIdNumberIgnoreCase(String idNumber);
    Manager findByIdNumberIgnoreCase(String idNumber);
}
