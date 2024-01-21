package com.example.MonthlyPaymentCalculator.repository;

import com.example.MonthlyPaymentCalculator.models.Prospect;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProspectRepository extends JpaRepository<Prospect,Long> {
}
