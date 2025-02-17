package com.rci.wyndham.serviceimpl;


import com.rci.wyndham.entity.NabDirectPostTransactions;
import com.rci.wyndham.enums.PaymentSourceSystemEnum;
import com.rci.wyndham.model.BaseObject;
import com.rci.wyndham.model.Payment;
import com.rci.wyndham.service.NabDirectPostTransactionsService;
import com.rci.wyndham.service.PaymentService;
import com.rci.wyndham.service.PaymentServiceFactory;
import com.rci.wyndham.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class TransactionServiceImpl extends BaseObject implements TransactionService {

    @Autowired
    private NabDirectPostTransactionsService nabDirectPostTransactionsService;

    @Autowired
    private PaymentServiceFactory paymentServiceFactory;

    @Autowired
    @Qualifier("hubService")
    private HubServiceFactory hubServiceFactory;

    @Autowired
    private TemplateService templateService;

    @Value("${cybersource.default.currency}")
    private String defaultCurrency;

    @Value("${transaction.payment.type}")
    private String transactionPaymentType;

    @Value("${transaction.timestamp.date.format}")
    private String transactionTimestampFormat;

    @Value("${cybersource.reason.code.successful}")
    private String successfulReasonCode;

    @Value("${payment.receipt.from.email}")
    private String receiptFromEmail;

    @Value("${payment.receipt.from.name}")
    private String receiptFromName;

    @Value("${mailer.service.client.name}")
    private String mailerServiceClientName;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public void save(String uniqid, String fingerPrint, Payment payment, String merchantId) {
        LOGGER.info("saving payment for unique id >> " + uniqid);
        LOGGER.info("finger print >> " + fingerPrint);
        if (payment != null) {
            PaymentSourceSystemEnum sourceSystem = payment.getSourceSystem();
            PaymentService paymentService = paymentServiceFactory.getInstance(sourceSystem);
            payment = paymentService.save(payment);
            LOGGER.info("payment successfully created >> " + payment);

            LOGGER.info("creating the transaction");
            NabDirectPostTransactions transaction = new NabDirectPostTransactions();
            transaction.setId(uniqid);
            transaction.setSourceSystem(payment.getSourceSystem().getName());
            transaction.setSourceSystemPk(payment.getId());
            transaction.setMerchantId(merchantId);
            transaction.setAmount(payment.getAmount());
            transaction.setCurrency(defaultCurrency);
            transaction.setTimeStamp(WynDateUtil.dateToString(new Date(), transactionTimestampFormat));
            transaction.setFingerprint(fingerPrint);
            transaction.setPaymentType(transactionPaymentType);
            transaction.setReference(payment.getReferenceNumber());
            transaction.setCreatedTs(new Date());
            nabDirectPostTransactionsService.save(transaction);

            LOGGER.info("transaction successfully created for unique id >> " + uniqid);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public NabDirectPostTransactions updateTransaction(String transactionUuid, String reasonCode, TransactionDecisionEnum decision, String transactionId, String authTime,
                                                       String authCode, String pan, String expiryDate) {
        LOGGER.info("updating transaction id:" + transactionUuid);
        LOGGER.info("reason code >> " + reasonCode);
        NabDirectPostTransactions transaction = nabDirectPostTransactionsService.getTransactionById(transactionUuid);
        if (transaction != null) {
            LOGGER.info("Transaction found for transactionUuid:" + transactionUuid);
            String sourceSystem = transaction.getSourceSystem();
            PaymentSourceSystemEnum sourceSystemEnum = PaymentSourceSystemEnum.loadByName(sourceSystem);
            PaymentService paymentService = paymentServiceFactory.getInstance(sourceSystemEnum);
            Integer sourceSystemPk = transaction.getSourceSystemPk();

            LOGGER.info("transaction found for source system:" + sourceSystemEnum);

            //update the transaction
            transaction.setResCode(reasonCode);
            transaction.setResText(decision.name());
            transaction.setPan(pan);
            transaction.setTransactionId(transactionId);

            String formattedExpiryDate = WVRAPUtil.formatExpiryDate(expiryDate);
            transaction.setExpiryDate(formattedExpiryDate);

            boolean isSuccessfulTransaction = isSuccessful(transaction);

            LOGGER.info("updating payment for transactionUuid:" + transactionUuid);

            Payment payment = paymentService.updatePayment(sourceSystemPk, isSuccessfulTransaction);

            LOGGER.info("saving transaction for transactionUuid:" + transactionUuid);

            if (isSuccessfulTransaction) {
                transaction.setSettlementDate(WynDateUtil.dateToString(new Date(), "yyyyMMdd"));
                //sendEmailReceipt(payment, transaction);
            }

            return nabDirectPostTransactionsService.save(transaction);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createRetryTransaction(String newUniqueId, String newFingerprint, NabDirectPostTransactions transaction) throws Exception {
        try {
            LOGGER.info("creating retry transaction for unique id:" + newUniqueId);
            if (!canRetry(transaction)) {
                throw new Exception("Payment not valid for retry.");
            }

            NabDirectPostTransactions clone = (NabDirectPostTransactions) transaction.clone();
            clone.setId(newUniqueId);
            clone.setTimeStamp(WynDateUtil.dateToString(new Date(), transactionTimestampFormat));
            clone.setFingerprint(newFingerprint);
            clone.setResCode(null);
            clone.setResText(null);
            clone.setCreatedTs(null);
            clone.setModifiedTs(null);

            nabDirectPostTransactionsService.save(clone);

        } catch (Exception e) {
            LOGGER.error("could not clone object", e);
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSuccessful(NabDirectPostTransactions transaction) {
        if (transaction != null) {
            return StringUtils.equals(StringUtils.trim(transaction.getResCode()), successfulReasonCode);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canRetry(NabDirectPostTransactions transaction) {
        // do not retry if payment was successful
        if (isSuccessful(transaction)) {
            return false;
        }

        // do not retry if previous payment is older than 30 mins
        String timeStamp = transaction.getTimeStamp();
        Date transactionDate = WynDateUtil.stringToDate(timeStamp, transactionTimestampFormat);
        Date now = new Date();
        if (now.getTime() - transactionDate.getTime() > 30 * 60 * 1000) {
            return false;
        }

        // this transaction is valid for a retry
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NabDirectPostTransactions getTransaction(String transactionUuid) {
        return nabDirectPostTransactionsService.getTransactionById(transactionUuid);
    }

    @Override
    public void sendEmailReceipt(Payment payment, NabDirectPostTransactions transaction) {
        try {
            LOGGER.info("sending email receipt");

            if (payment.allowedToReceiveEmailReceipt()) {
                MailerService mailerService = hubServiceFactory.getMailerService();

                PaymentSourceSystemEnum sourceSystem = payment.getSourceSystem();
                String transactionId = transaction.getTransactionId();

                EmailMessage emailMessage = new EmailMessage();
                emailMessage.setClientName(mailerServiceClientName);
                emailMessage.setMailerType(EmailMessage.MailerType.SMTP);
                emailMessage.setSubject("Transaction # " + transactionId + " payment receipt");

                HashMap<String, Object> values = new HashMap<>();
                values.put("description", payment.getDescription());

                String merchantId = "";
                if (!sourceSystem.equals(PaymentSourceSystemEnum.WMSP_Finance)) {
                    merchantId = "WorldMark South Pacific Club by Wyndham";
                } else if (!sourceSystem.equals(PaymentSourceSystemEnum.WMSP_Finance)) {
                    merchantId = "WorldMark South Pacific Club by Wyndham";
                }
                values.put("merchantId", merchantId);
                values.put("pan", transaction.getPan());
                values.put("expiryDate", transaction.getExpiryDate());

                double amount = transaction.getAmount();
                values.put("amount", WynMoneyUtil.formatMoneyWithCurrency(new BigDecimal(amount)));

                values.put("reference", transaction.getReference());
                values.put("reasonCode", transaction.getResCode());
                values.put("reasonText", transaction.getResText());
                values.put("settlementDate", transaction.getSettlementDate());
                values.put("txnId", transactionId);

                String source = "Finance by Wyndham";
                if (!sourceSystem.equals(PaymentSourceSystemEnum.WMSP_Finance)) {
                    source = "WorldMark South Pacific Club by Wyndham";
                }

                values.put("source", source);
                String plainText = templateService.getTemplateOutput("emails/base_payment_email_template_plaintext.ftlh", values);
                String html = templateService.getTemplateOutput("emails/base_payment_email_template.ftlh", values);

                emailMessage.setText(plainText);
                emailMessage.setHtml(html);
                List<EmailRecipient> recipients = new ArrayList<>();

                //Main recipient
                EmailRecipient emailRecipient = new EmailRecipient();
                emailRecipient.setType(EmailRecipient.Type.TO);
                String email = payment.getEmail();
                if (StringUtils.isNotBlank(email)) {
                    emailRecipient.setEmail(email);
                    emailRecipient.setName(payment.getFirstName());

                    recipients.add(emailRecipient);
                    emailMessage.setRecipients(recipients);

                    emailMessage.setFromEmail(receiptFromEmail);
                    emailMessage.setFromName(receiptFromName);

                    EmailResponse emailResponse = mailerService.sendMail(emailMessage);

                    EmailResponse.Status status = emailResponse.getStatus();
                    if (status.equals(EmailResponse.Status.SENT)) {
                        LOGGER.info("Sent receipt email for order: " + payment.getId() + " to: " + email);
                        payment.incrementReceiptSentCount();
                        PaymentService paymentService = paymentServiceFactory.getInstance(sourceSystem);
                        paymentService.save(payment);
                    } else {
                        LOGGER.error("Failed sending receipt email for order: " + payment.getId() + " to: " + email);
                        LOGGER.error("EmailResponse.Status:" + status + "::Message:" + emailResponse.getMessage());
                    }
                } else {
                    LOGGER.error("could not find an email to send the receipt::transactionId:" + transactionId);
                }
            } else {
                LOGGER.info("not allowed to receive email receipt");
            }
        } catch (Exception e) {
            LOGGER.error("error while sending email receipt", e);
        }
    }
}
