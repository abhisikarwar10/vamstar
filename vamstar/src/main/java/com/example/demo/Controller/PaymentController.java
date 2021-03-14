package com.example.demo.Controller;

import com.braintreegateway.*;
import com.example.demo.Configuration.BraintreeGatewayFactory;
import com.example.demo.DTO.BraintreeTransactionDTO;
import com.example.demo.Exceptions.TransactionException;
import com.example.demo.Model.Order;
import com.example.demo.Model.PaymentInformation;
import com.example.demo.Service.BraintreeService;
import com.example.demo.Service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Arrays;

@Controller
public class PaymentController {

    private final Transaction.Status[] TRANSACTION_SUCCESS_STATUSES = new Transaction.Status[] {
            Transaction.Status.AUTHORIZED,
            Transaction.Status.AUTHORIZING,
            Transaction.Status.SETTLED,
            Transaction.Status.SETTLEMENT_CONFIRMED,
            Transaction.Status.SETTLEMENT_PENDING,
            Transaction.Status.SETTLING,
            Transaction.Status.SUBMITTED_FOR_SETTLEMENT
    };

    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @Autowired
    BraintreeService braintreeService;

    @Autowired
    PaypalService paypalService;

    Logger logger = LoggerFactory.getLogger(PaymentController.class);


    @PostMapping(value = "/checkouts")
    public String postForm(PaymentInformation paymentInformation, Model model) throws TransactionException {
        String transactionId;
    try{
        transactionId = braintreeService.processPayment(paymentInformation);}
    catch(HttpStatusCodeException e){
        model.addAttribute("errorStatusText",e.getMessage());
        model.addAttribute("errorStatusCode", HttpStatus.BAD_REQUEST);
            return "error";
        }

    return "redirect:checkouts/" + transactionId;
    }

    @RequestMapping (value = "/checkouts/{transactionId}")
    public String getTransaction(@PathVariable String transactionId, Model model) {
        BraintreeTransactionDTO braintreeTransactionDTO;
        try {
             braintreeTransactionDTO = braintreeService.getTransaction(transactionId);
        }
        catch (Exception e){
            return "redirect:/checkouts";
        }

        model.addAttribute("isSuccess", Arrays.asList(TRANSACTION_SUCCESS_STATUSES).contains(braintreeTransactionDTO.getTransaction().getStatus()));
        model.addAttribute("transaction", braintreeTransactionDTO.getTransaction());
        model.addAttribute("creditCard", braintreeTransactionDTO.getCreditCard());
        model.addAttribute("customer", braintreeTransactionDTO.getCustomer());

        return "displayDetails";
    }


    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
        try {

            Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getClientName(), "http://localhost:9090/" + CANCEL_URL,
                    "http://localhost:9090/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId,Model model) {
        try {

            Payment payment = paypalService.executePayment(paymentId, payerId);
            model.addAttribute("payment",payment.toString());
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }


}

