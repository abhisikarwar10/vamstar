package com.example.demo.DTO;

import com.braintreegateway.CreditCard;
import com.braintreegateway.Customer;
import com.braintreegateway.Transaction;

import java.io.Serializable;

public class BraintreeTransactionDTO implements Serializable {



    Transaction transaction;
    CreditCard creditCard;
    Customer customer;

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Override
    public String toString() {
        return "BraintreeTransactionDTO{" +
                "transaction=" + transaction +
                ", creditCard=" + creditCard +
                ", customer=" + customer +
                '}';
    }
}
