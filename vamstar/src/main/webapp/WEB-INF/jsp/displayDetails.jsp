<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>





<div class="wrapper">
    <div class="response container">
        <div class="content">
            <div class="icon">

            </div>


                <h1>Sweet Success!</h1>
                <section>Your test transaction has been successfully processed.</section>
            </div>


            <section>
                <a class="button primary back" href="/">
                    <span>Test Another Transaction</span>
                </a>
            </section>
        </div>
    </div>
</div>

<aside class="drawer dark">
    <header>
        <div class="content compact">
            <h3>API Response</h3>
        </div>
    </header>

    <article class="content compact">
        <section>
            <h5>Transaction</h5>
            <table cellpadding="0" cellspacing="0">
                <tbody>
                <tr>
                    <td>id</td>
                    <td text="">${transaction.getId()}</td>
                </tr>
                <tr>
                    <td>type</td>
                    <td text="">${transaction.getType()}</td>
                </tr>
                <tr>
                    <td>amount</td>
                    <td text="">${transaction.getAmount()}</td>
                </tr>
                <tr>
                    <td>status</td>
                    <td text="">${transaction.getStatus()}</td>
                </tr>
                <tr>
                    <td>created_at</td>
                    <td text="">${transaction.getCreatedAt().getTime()}</td>
                </tr>
                <tr>
                    <td>updated_at</td>
                    <td text="">${transaction.getUpdatedAt().getTime()}</td>
                </tr>
                </tbody>
            </table>
        </section>

        <section>
            <h5>Payment</h5>

            <table cellpadding="0" cellspacing="0">
                <tbody>

                <tr>
                    <td>bin</td>
                    <td >${creditCard.getBin()}</td>
                </tr>
                <tr>
                    <td>last_4</td>
                    <td >${creditCard.getLast4()}</td>
                </tr>
                <tr>
                    <td>card_type</td>
                    <td >${creditCard.getCardType()}</td>
                </tr>
                <tr>
                    <td>expiration_date</td>
                    <td >${creditCard.getExpirationDate()}</td>
                </tr>
                <tr>
                    <td>cardholder_name</td>
                    <td >${creditCard.getCardholderName()}</td>
                </tr>
                <tr>
                    <td>customer_location</td>
                    <td >${creditCard.getCustomerLocation()}</td>
                </tr>
                </tbody>
            </table>
        </section>


            <h5>Customer Details</h5>

            <table cellpadding="0" cellspacing="0">
                <tbody>

                <tr>
                    <td>name</td>
                    <td >${customer.getFirstName()}</td>
                </tr>

                </tbody>
            </table>




    </article>
</aside>


