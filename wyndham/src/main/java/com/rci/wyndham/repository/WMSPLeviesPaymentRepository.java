package com.rci.wyndham.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rci.wyndham.entity.WMSPLeviesPayment;

@Repository
public interface WMSPLeviesPaymentRepository extends JpaRepository<WMSPLeviesPayment, Integer>{
    
    Optional<WMSPLeviesPayment> findById(Integer id);
}
