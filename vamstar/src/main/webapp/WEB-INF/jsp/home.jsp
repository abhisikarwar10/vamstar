<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<head>
    <link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/bootstrap-extended.min.css">
    <link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/fonts/simple-line-icons/style.min.css">
    <link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/colors.min.css">
    <link rel="stylesheet" type="text/css" href="https://pixinvent.com/stack-responsive-bootstrap-4-admin-template/app-assets/css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Credit Card Validation Demo</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <link rel="stylesheet" type="text/css" href="/css/demo.css">



</head>
<div id="main-area">
<div>
<form id="detailsForm" action="/pay" method="POST">
    <div class="form-group">
        <label for="price">Price</label>
        <input type="Number" class="form-control" id="price" placeholder="Enter price in dollars" name="price">
    </div>
    <div class="form-group">

        <label for="currency">Choose a Currency:</label>
        <select id="currency" name="currency">
            <option value="USD">USD</option>
            <option value="EUR">EUR</option>
            <option value="THB">THB</option>
            <option value="HKD">HKD</option>
            <option value="SGD">SGD</option>
            <option value="AUD">AUD</option>
        </select>

    </div>
    <div class="form-group">
        <label for="clientName">Name</label>
        <input type="text" class="form-control" id="clientName" placeholder="Enter Name" name="clientName">
    </div>
    <a type="submit" onclick="this.parentNode.submit()" class="btn btn-default" id="paypal">Pay With Paypal</a>

    <h1> or enter details below to pay with braintree</h1>

    <div class="container-fluid" id="paymentForm">
        <header>
            <div class="limiter">
                <h3>Payment page</h3>
            </div>
        </header>
        <div class="creditCardForm">
            <div class="heading">
                <h1>Confirm Purchase</h1>
            </div>
            <div class="payment" >

                    <div class="form-group owner">
                        <label for="ownerName">Owner</label>
                        <input type="text" class="form-control" id="ownerName" name="ownerName">
                    </div>
                    <div class="form-group CVV">
                        <label for="cvv">CVV</label>
                        <input type="text" class="form-control" id="cvv" name="cvv">
                    </div>
                    <div class="form-group" id="card-number-field">
                        <label for="cardNumber">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber">
                    </div>
                    <div class="form-group" id="expiration-date">
                        <label>Expiration Date</label>
                        <select name="expiryMonth" id="expiryMonth">
                            <option value="01">January</option>
                            <option value="02">February </option>
                            <option value="03">March</option>
                            <option value="04">April</option>
                            <option value="05">May</option>
                            <option value="06">June</option>
                            <option value="07">July</option>
                            <option value="08">August</option>
                            <option value="09">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                        <select name="expiryYear" id="expiryYear">
                            <option value="21"> 2021</option>
                            <option value="22"> 2022</option>
                            <option value="23"> 2023</option>
                            <option value="24"> 2024</option>
                            <option value="25"> 2025</option>
                            <option value="26"> 2026</option>
                        </select>
                    </div>
                    <div class="form-group" id="credit_cards">
                        <img src="/images/visa.jpg" id="visa">
                        <img src="/images/mastercard.jpg" id="mastercard">
                        <img src="/images/amex.jpg" id="amex">
                    </div>

            </div>
        </div>
</div>
        <button type="submit" class="btn btn-default" id="confirm-purchase">Pay With braintree</button>
</form>
</div>

        <p class="examples-note">Here are some dummy credit card numbers and CVV codes so you can test out the form:</p>

        <div class="examples">
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Type</th>
                        <th>Card Number</th>
                        <th>Security Code</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Visa</td>
                        <td>4716108999716531</td>
                        <td>257</td>
                    </tr>
                    <tr>
                        <td>Master Card</td>
                        <td>5281037048916168</td>
                        <td>043</td>
                    </tr>
                    <tr>
                        <td>American Express</td>
                        <td>342498818630298</td>
                        <td>3156</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="/js/jquery.payform.min.js" charset="utf-8"></script>
    <script src="/js/script.js"></script>
</div>

