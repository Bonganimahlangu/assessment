package com.xib.assessment.dao;

import com.xib.assessment.domain.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    boolean existsByIdNumber(String idNumber);

    Page<Agent> findAll(Pageable pageable);
}
