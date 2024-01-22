package com.example.MonthlyPaymentCalculator.config;

import com.example.MonthlyPaymentCalculator.models.Prospect;
import com.example.MonthlyPaymentCalculator.repository.ProspectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.MonthlyPaymentCalculator.Calculate.initialData;

@Configuration
public class ProspectConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProspectRepository repository){
        return args -> {
        repository.saveAll(List.of(initialData()));
        };
    }
}
