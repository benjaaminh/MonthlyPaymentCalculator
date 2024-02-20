package com.example.MonthlyPaymentCalculator;

import com.example.MonthlyPaymentCalculator.baseApp.Calculate;
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
				repository.deleteAll();
				Prospect p = new Prospect("test",2000,2,2, Calculate.getMonthlyPayment(2000,2,2));
				repository.save(p);
				assertEquals(1,repository.findAll().size());
	}
	@Test
	void testDeleteProspect(){
		repository.deleteAll();
		Prospect p = new Prospect("test",2000,2,2, Calculate.getMonthlyPayment(2000,2,2));
		repository.save(p);
		repository.deleteById(1L);
		assertEquals(1,repository.findAll().size());
	}

}
