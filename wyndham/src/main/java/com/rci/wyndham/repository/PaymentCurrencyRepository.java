package com.rci.wyndham.repository;

import com.rci.wyndham.entity.PaymentCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCurrencyRepository extends JpaRepository<PaymentCurrency, Integer> {

    PaymentCurrency findByName(String name);
}
