package com.gestionFinanzas.Troncales.Investment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("investments")
public class InvestmentController {

    private InvestmentService investmentService;

    // Inyección del servicio de inversiones
    @Autowired
    public void setInvestmentService(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GetMapping("/get-all-investments")
    public List<Investment> getALLInvestments() {
        return investmentService.getALLInvestments();
    }

}
