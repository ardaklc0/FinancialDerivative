package com.test.demo.repository;

import com.test.demo.model.RiskNeutral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskNeutralRepository extends JpaRepository<RiskNeutral, Long> {
}
