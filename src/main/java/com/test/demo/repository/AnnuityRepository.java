package com.test.demo.repository;

import com.test.demo.model.Annuity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnuityRepository extends JpaRepository<Annuity, Long> {
}
