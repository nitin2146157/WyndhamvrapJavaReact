package com.rci.wyndham.repository;

import com.rci.wyndham.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Integer> {

    PaymentType findByName(String name);
}
