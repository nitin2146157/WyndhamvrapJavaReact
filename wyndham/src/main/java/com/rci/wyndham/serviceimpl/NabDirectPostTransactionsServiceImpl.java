package com.rci.wyndham.serviceimpl;

import com.rci.wyndham.entity.NabDirectPostTransactions;
import com.rci.wyndham.model.BaseObject;
import com.rci.wyndham.repository.NabDirectPostTransactionsRepository;
import com.rci.wyndham.service.NabDirectPostTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NabDirectPostTransactionsServiceImpl extends BaseObject implements NabDirectPostTransactionsService {

    @Autowired
    private NabDirectPostTransactionsRepository nabDirectPostTransactionsRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public NabDirectPostTransactions save(NabDirectPostTransactions transaction) {
        return nabDirectPostTransactionsRepository.save(transaction);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NabDirectPostTransactions getTransactionById(String transactionUuid) {
        LOGGER.info("getting transaction by id >> " + transactionUuid);
        Optional<NabDirectPostTransactions> transaction = nabDirectPostTransactionsRepository.findById(transactionUuid);
        return transaction.orElse(null);
    }

}