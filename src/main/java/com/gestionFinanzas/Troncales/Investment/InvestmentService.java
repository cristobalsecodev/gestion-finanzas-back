package com.gestionFinanzas.Troncales.Investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    private InvestmentRepository investmentRepository;

    // Inyección del repoository
    @Autowired
    public void setInvestmentRepository(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }

    public List<Investment> getALLInvestments() {
        return investmentRepository.findAll();
    }

}
