package com.rci.wyndham.service;

import com.rci.wyndham.entity.NabDirectPostTransactions;

public interface NabDirectPostTransactionsService {

    /**
     * Saves the given transaction.
     * @param transaction the transaction.
     */
    NabDirectPostTransactions save(NabDirectPostTransactions transaction);

    /**
     * Gets the transaction by transaction unique id.
     * @param transactionUuid the transaction unique id
     * @return the transaction
     */
    NabDirectPostTransactions getTransactionById(String transactionUuid);

}