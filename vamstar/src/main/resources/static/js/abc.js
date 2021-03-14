$(function() {
let orderId;

// Displays PayPal buttonsYOUR-CLIENT-ID
paypal.Buttons({
    style: {
        layout: 'horizontal'
    },
    createOrder: function(data, actions) {
        return actions.order.create({
            purchase_units: [{
                amount: {
                    value: "1.00"
                }
            }]
        });
    },
    onApprove: function(data, actions) {
        return actions.order.capture().then(function(details) {
            window.location.href = '/success.html';
        });
    }
}).render("#paypal-button-container");

// If this returns false or the card fields aren't visible, see Step #1.
if (paypal.HostedFields.isEligible()) {

    // Renders card fields
    paypal.HostedFields.render({
        // Call your server to set up the transaction
        createOrder: function () {
            return fetch('/pay', {
                method: 'post'
            }).then(function(res) {
                return res.json();
            }).then(function(orderData) {
                orderId = orderData.id;
                return orderId;
            });
        },

        styles: {
            '.valid': {
                'color': 'green'
            },
            '.invalid': {
                'color': 'red'
            }
        },

        fields: {
            number: {
                selector: "#card-number",
                placeholder: "4111 1111 1111 1111"
            },
            cvv: {
                selector: "#cvv",
                placeholder: "123"
            },
            expirationDate: {
                selector: "#expiration-date",
                placeholder: "MM/YY"
            }
        }
    }).then(function (cardFields) {
        document.querySelector("#card-form").addEventListener('submit', (event) => {
            event.preventDefault();

            cardFields.submit({
                // Cardholder's first and last name
                cardholderName: document.getElementById('card-holder-name').value,
                // Billing Address
                billingAddress: {
                    // Street address, line 1
                    streetAddress: document.getElementById('card-billing-address-street').value,
                    // Street address, line 2 (Ex: Unit, Apartment, etc.)
                    extendedAddress: document.getElementById('card-billing-address-unit').value,
                    // State
                    region: document.getElementById('card-billing-address-state').value,
                    // City
                    locality: document.getElementById('card-billing-address-city').value,
                    // Postal Code
                    postalCode: document.getElementById('card-billing-address-zip').value,
                    // Country Code
                    countryCodeAlpha2: document.getElementById('card-billing-address-country').value
                }
            }).then(function () {
                // Payment was successful! Show a notification or redirect to another page.
                window.location.replace('http://www.somesite.com/review');
            }).catch(function (err) {
                alert('Payment could not be captured! ' + JSON.stringify(err))
            });
        });
    });
} else {
    // Hides card fields if the merchant isn't eligible
    document.querySelector("#card-form").style = 'display: none';
}
});