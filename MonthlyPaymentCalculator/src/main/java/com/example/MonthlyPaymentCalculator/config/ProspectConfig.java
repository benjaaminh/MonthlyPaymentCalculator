package com.example.MonthlyPaymentCalculator.config;

import com.example.MonthlyPaymentCalculator.repository.ProspectRepository;

import jakarta.servlet.SessionTrackingMode;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

import static com.example.MonthlyPaymentCalculator.InitialData.initialData;

@Configuration
public class ProspectConfig {

    //to prevent thymeleaf from appending sessionid to static resources
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            servletContext.setSessionTrackingModes(Set.of(SessionTrackingMode.COOKIE));
        };
    }

    @Bean
    CommandLineRunner commandLineRunner(ProspectRepository repository) {
        return args -> {
            repository.saveAll(List.of(initialData()));
        };
    }
}
