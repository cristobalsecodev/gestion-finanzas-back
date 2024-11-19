package com.gestionFinanzas.Shared.CurrencyExchangeRatio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyExchangeRatioRepository extends JpaRepository<CurrencyExchangeRatio, Integer> {

    @Query(value = "SELECT cer FROM CurrencyExchangeRatio cer WHERE cer.currencyCode = :currencyCode")
    CurrencyExchangeRatio findByCurrencyCode(@Param("currencyCode") String currencyCode);

    @Query(value = "SELECT cer FROM CurrencyExchangeRatio cer")
    List<CurrencyExchangeRatio> getCurrencies();


}
