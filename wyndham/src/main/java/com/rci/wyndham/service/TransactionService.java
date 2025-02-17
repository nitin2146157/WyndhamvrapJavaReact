package com.rci.wyndham.service;

import com.rci.wyndham.entity.NabDirectPostTransactions;
import com.rci.wyndham.model.Payment;
public interface TransactionService {

    /**
     * Saves a payment and creates a transaction linked to it given a unique id and a finger print.
     * @param uniqid the unique id
     * @param fingerPrint the finger print
     * @param payment the {@link Payment}
     */
    void save(String uniqid, String fingerPrint, Payment payment, String merchantId);

    /**
     * Updates the transaction given a list of parameters.
     * @param transactionUuid the transaction id
     * @param reasonCode the reason code
     * @param decision the decision
     * @param transactionId the transaction id
     * @param authTime the auth time
     * @param authCode the auth code
     */
    NabDirectPostTransactions updateTransaction(String transactionUuid, String reasonCode, TransactionDecisionEnum decision, String transactionId, String authTime, String authCode, String pan, String expiryDate);

    /**
     * Creates a retry transaction based on a new unique id, a new finger print and the existing transaction.
     * @param newUniqueId the unique id
     * @param newFingerprint the finger print
     * @param transaction the transaction
     * @throws Exception
     */
    void createRetryTransaction(String newUniqueId, String newFingerprint, NabDirectPostTransactions transaction) throws Exception;

    /**
     * Returns true of the transaction was successful.
     * @param transaction the transaction
     * @return tre if successful, false otherwise
     */
    boolean isSuccessful(NabDirectPostTransactions transaction);

    /**
     * Checks if the given transaction can be tried again.
     * @param transaction the transaction
     * @return true if yes
     */
    boolean canRetry(NabDirectPostTransactions transaction);

    /**
     * Gets the transaction given its transaction unique id.
     * @param transactionUuid the transaction unique id
     * @return the {@link NabDirectPostTransactions}
     */
    NabDirectPostTransactions getTransaction(String transactionUuid);

    void sendEmailReceipt(Payment payment, NabDirectPostTransactions transaction);
}
