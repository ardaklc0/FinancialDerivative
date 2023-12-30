package com.test.demo.repository;

import com.test.demo.model.Amortization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmortizationRepository extends JpaRepository<Amortization, Long> {
}
