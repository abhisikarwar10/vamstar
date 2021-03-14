package com.example.demo.Service;

import com.braintreegateway.*;
import com.example.demo.DTO.BraintreeTransactionDTO;
import com.example.demo.Exceptions.TransactionException;
import com.example.demo.Model.PaymentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class BraintreeService {

    @Autowired
    public BraintreeGateway braintreeGateway;;

    public String processPayment(PaymentInformation paymentInformation) throws TransactionException {
        BigDecimal decimalAmount = new BigDecimal(paymentInformation.getPrice());;

        TransactionRequest request = new TransactionRequest()
                .amount(decimalAmount)
                .creditCard()
                .cvv(paymentInformation.getCvv())
                .cardholderName(paymentInformation.getOwnerName())
                .number(paymentInformation.getCardNumber().replaceAll(" ",""))
                .expirationMonth(paymentInformation.getExpiryMonth())
                .expirationYear(paymentInformation.getExpiryYear())
                .done()
                .customer()
                .firstName(paymentInformation.getOwnerName())
                .done()
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = braintreeGateway.transaction().sale(request);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            return transaction.getId();
        } else if (result.getTransaction() != null) {
            Transaction transaction = result.getTransaction();
            return transaction.getId();
        } else {
            StringBuilder errorString = new StringBuilder();
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                errorString.append("Error: ").append(error.getCode()).append(": ").append(error.getMessage()).append("\n");
            }
            throw new TransactionException(errorString.toString());
        }
    }


    public BraintreeTransactionDTO getTransaction(String transactionId) {
        Transaction transaction;
        CreditCard creditCard;
        Customer customer;
        BraintreeTransactionDTO braintreeTransactionDTO = new BraintreeTransactionDTO();

        try {
            transaction = braintreeGateway.transaction().find(transactionId);
            creditCard = transaction.getCreditCard();
            customer = transaction.getCustomer();
        } catch (Exception e) {
            throw e;
        }
        braintreeTransactionDTO.setTransaction(transaction);
        braintreeTransactionDTO.setCreditCard(creditCard);
        braintreeTransactionDTO.setCustomer(customer);


        return braintreeTransactionDTO;

    }
}

