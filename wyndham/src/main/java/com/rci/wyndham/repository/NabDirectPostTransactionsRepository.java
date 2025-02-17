package com.rci.wyndham.repository;

import com.rci.wyndham.entity.NabDirectPostTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NabDirectPostTransactionsRepository extends JpaRepository<NabDirectPostTransactions, String> {

    Optional<NabDirectPostTransactions> findById(String id);
}
