package com.gestionFinanzas.Categories.Investment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestmentCategoryRepository extends JpaRepository<InvestmentCategory, Long> {



}
