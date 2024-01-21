package com.example.MonthlyPaymentCalculator;

import com.example.MonthlyPaymentCalculator.models.Prospect;
import com.example.MonthlyPaymentCalculator.repository.ProspectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MonthlyPaymentCalculatorApplication.class)
class MonthlyPaymentCalculatorApplicationTests {

	@Autowired
	public MonthlyPaymentCalculatorApplicationTests(ProspectRepository repo){
		this.repository=repo;
	}
	ProspectRepository repository;
	@Test
	void contextLoads() {
	}

	@Test
	void testAddProspect(){
				Prospect p = new Prospect("test",2000,2,2,Calculate.getMonthlyPayment(2000,2,2));
				repository.save(p);
				assertTrue(repository.findAll().size()>4);
	}
	@Test
	void testDeleteProspect(){
		repository.deleteById(1L);
		assertTrue(repository.findAll().size()<4);
	}

}
