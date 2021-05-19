package com.example.web;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Optional;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.loading.PrivateMLet;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;


import lombok.extern.slf4j.Slf4j;
import lombok.Getter;
import lombok.Setter;

import com.example.model.Drink;
import com.example.model.Order;
import com.example.springcybersource.*;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Controller
@RequestMapping
public class PaymentsCommandController {

    private static boolean DEBUG = true;
    @Value ("${cybersource.apihost}") String apiHost;
    @Value ("${cybersource.merchantkeyid}") String merchantKeyId;
    @Value ("${cybersource.merchantsecretkey}") String merchantsecretkey;
    @Value ("${cybersource.merchantid}") String merchantId;

    // String apiHost = "apitest.cybersource.com";
    // String merchantKeyId = "8084313d-ba4c-4135-bc38-9fe5d29ccb4b";
    // String merchantsecretkey = "1MqZkPNXPdEXIcfW2ORRodZ7BN7SCg1Xvr/QScGpZVE=";
    // String merchantId ="anhthoang";

    private CyberSourceAPI api = new CyberSourceAPI();
    private String orderName = "0.00";
    private String totalAmount = "0.00";
    private String orderType = "0.00";


    private static Map<String,String> months = new HashMap<>();
    static {
        months.put("January", "01");
        months. put("February", "02");
        months.put("March", "03");
        months.put("April", "04");
        months.put("May", "05");
        months.put("June", "06");
        months.put("July", "07");
        months.put("August","08");
        months.put("September", "09");
        months.put("October", "10");
        months.put("November", "11");
        months.put("December","12");
    }

    @Autowired
    private PaymentsCommandRepository repository;

    @GetMapping("/payments")
    public String getAction( @ModelAttribute("command") PaymentsCommand command, 
                            Model model) {

        model.addAttribute("transactionAmount", "$totalAmount");
        return "addpayment" ;

    }

    @Getter
    @Setter
    class Message{
        private String msg;
        public Message(String m){ msg = m;}

    }

    class ErrorMessages{
        private ArrayList<Message> messages = new ArrayList<Message>();
        public void add(String msg){
            messages.add(new Message(msg));
        }
        public ArrayList<Message> getMessages(){return messages;}
        public void print(){
            for (Message m : messages){
                System.out.println(m.msg);
            }
        }
    }
    @PostMapping("/payments")
    public String postAction(@Valid @ModelAttribute("command") PaymentsCommand command,  
            @RequestParam(value = "action", required = true) String action, @Valid @ModelAttribute("order") Order order,
            Errors errors, Model model, HttpServletRequest request) {

      
        try {

            log.info("Action: " + action);
            log.info("Command: " + command.getTransactionAmount());
    
            try{
                String unitOrder = order.getDrinkToString();
                String[] arrayOrder = unitOrder.split("-");
                if (arrayOrder != null) {
                    orderName = arrayOrder[0];
                    totalAmount = arrayOrder[1];
                    orderType = arrayOrder[2];
                }
            }
            catch (NullPointerException e){

            }
    
            System.out.println("arrayOrder");
            System.out.println(orderName);
            System.out.println("totalAmount");
            System.out.println(totalAmount);
            System.out.println("orderType");
            System.out.println(orderType);

            model.addAttribute("ordername", orderName);
            model.addAttribute("transactionAmount", totalAmount);
            model.addAttribute("ordertype", orderType);
            model.addAttribute("datetoday", new Date().toString());

            CyberSourceAPI.setHost(apiHost);
            CyberSourceAPI.setKey(merchantKeyId);
            CyberSourceAPI.setSecret(merchantsecretkey);
            CyberSourceAPI.setMerchant(merchantId);

            CyberSourceAPI.debugConfig();

            ErrorMessages msgs = new ErrorMessages();
            /*Errors*/

            boolean hasErrors= false;
            if (command.cardname().equals(""))   {hasErrors = true; msgs.add("Credit Card Name Required");}
            if (command.cardnum().equals(""))   {hasErrors = true; msgs.add("Credit Card Number Required");}
            if (command.cardexpmon().equals(""))      {hasErrors = true; msgs.add("Credit Card Expiration Month Required");}
            if (command.cardexpyear().equals(""))     {hasErrors = true; msgs.add("Credit Card Expiration Year Required");}
            if (command.cardcvv().equals(""))       {hasErrors = true; msgs.add("Credit Card CVV Required");}

            //regex validation
            if (!command.cardnum().matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}")) {hasErrors = true; msgs.add("Invalid Card Number" + command.cardnum());}
            if (!command.cardexpyear().matches("\\d{4}")) {hasErrors = true; msgs.add("Invalid Card Expiration Year" + command.cardexpyear());}
            if (!command.cardcvv().matches("\\d{3}")) {hasErrors = true; msgs.add("Invalid Card CVV"+ command.cardcvv());}

            //validate month of year
            if (months.get(command.cardexpmon())==null) {hasErrors = true ; msgs.add("Invalid Card Expiration Month: "+ command.cardexpmon());}
            if (hasErrors){
                msgs.print();
                model.addAttribute("messages", msgs.getMessages());
                return "addpayment";
            }

            int min = 1239871;
            int max = 9999999;

            int random_int = (int) Math.floor(Math.random()*(max-min+1)+min);
            String order_num = String.valueOf(random_int);
            AuthRequest auth = new AuthRequest();
            auth.reference = order_num;
            auth.billToFirstName = command.cardname();
            auth.billToLastName = "Doe";
            auth.billToAddress = "123 alum rock";
            auth.billToCity = "san jose";
            auth.billToState = "CA";
            auth.billToZipCode = "12345";
            auth.billToPhone = "4081234567";
            auth.billToEmail= "email@yahoo.com";
            auth.transactionAmount = totalAmount;
            auth.transactionCurrency = "USD";  
            auth.cardNumber = command.cardnum();
            auth.cardExpMonth = months.get(command.cardexpmon());
            auth.cardExpYear = command.cardexpyear();
            auth.cardCVV = command.cardcvv();
            auth.cardType = CyberSourceAPI.getCardType (auth.cardNumber);
            if (auth.cardType.equals("ERROR")){
                System.out.println("Unsupported Credit Card Type");
                model.addAttribute("message", "Unsupported Credit Card Type");
                return "addpayment";
            }

            boolean authValid = true;
            AuthResponse authResponse = new AuthResponse();
            System.out.println("\n\nAuth Request: " + auth.toJson());
            authResponse= api.authorize(auth);
            System.out.println("\n\nAuth Response: " + authResponse.toJson());
            if(!authResponse.status.equals("AUTHORIZED")){
                authValid = false;
                System.out.println(authResponse.message);
                model.addAttribute("message", authResponse.message);
                return "addpayment";
            }

            boolean captureValid = true;
            CaptureRequest capture = new CaptureRequest();
            CaptureResponse captureResponse = new CaptureResponse();
            if (authValid){
                capture.reference = order_num;
                capture.paymentId = authResponse.id;
                capture.transactionAmount= totalAmount;
                capture.transactionCurrency = "USD";
                System.out.println("\n\nCapture Request: " +capture.toJson());
                captureResponse = api.capture(capture);
                System.out.println("\n\nCapture Response: "+ captureResponse.toJson());
                if(!captureResponse.status.equals("PENDING")){
                    captureValid = false;
                    System.out.println(captureResponse.message);
                    model.addAttribute("message", captureResponse.message);
                    return "addpayment";
                }

            }


            /* Render View */
            if (authValid && captureValid){
                command.setOrderNumber (order_num);
                command.setTransactionAmount(totalAmount);
                command.setAuthId(authResponse.id);
                command.setAuthStatus(authResponse.status);
                command.setCaptureId(captureResponse.id);
                command.setCaptureStatus(captureResponse.status);
            }

            repository.save(command);
            System.out.println("Thank you for your Payment! Your Order Number is :" + order_num);
            model.addAttribute( "message", "Thank You for Your Payment! Your Order Number is:" + order_num ) ;

            // repository.save(command);
            // System.out.println("Thank you for your Payment!");
            // model.addAttribute( "message", "Thank You for Your Payment") ;
        }
        catch(NullPointerException e){

        }
        return "addpayment";
    }
}


       /*

       log.info( "Action: " + action ) ;
            log.info( "Command: " + command.getTransactionAmount() ) ;
            System.out.println(order.getDrinkToString());
            
            String unitOrder = order.getDrinkToString();
            String[] arrayOrder = unitOrder.split("-");
        
            if (arrayOrder != null) {
                orderName = arrayOrder[0];
                totalAmount = arrayOrder[1];
                orderType = arrayOrder[2];
            }
            model.addAttribute("ordername", orderName);
            model.addAttribute("transactionAmount", totalAmount);
            model.addAttribute("ordertype", orderType);
            model.addAttribute("datetoday", new Date().toString());
import javax.validation.Valid;

import com.example.springcybersource.AuthRequest;
import com.example.springcybersource.AuthResponse;
import com.example.springcybersource.CaptureRequest;
import com.example.springcybersource.CaptureResponse;
import com.example.springcybersource.CyberSourceAPI;
import com.example.springcybersource.RefundRequest;
import com.example.springcybersource.RefundResponse;
import com.example.model.Order;
import com.example.model.PaymentCard;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.beans.factory.annotation.Value;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import java.time.Month;

@Slf4j
@Controller
@RequestMapping("/")
public class PaymentsCommandController {

    @Autowired
    private final PaymentCardRepository repository;
    private static boolean DEBUG = true;
    private String totalAmount = "0.00";

    @Value("${cybersource.apihost}")
    private String apiHost;
    @Value("${cybersource.merchantkeyid}")
    private String merchantKeyId;
    @Value("${cybersource.merchantsecretkey}")
    private String merchantsecretKey;
    @Value("${cybersource.merchantid}")
    private String merchantId;

    PaymentsCommandController(PaymentCardRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/payments")
    public String getAction(@ModelAttribute("command") PaymentsCommand command, Model model) {

        model.addAttribute("transactionAmount", "$3.91");
        return "addpayment";

    }

    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @PostMapping("/payments")
    public String postAction(@Valid @ModelAttribute("command") PaymentsCommand command,
            @Valid @ModelAttribute("order") Order order, BindingResult bindingResult, Model model) {

        boolean valid = true;
        int monthInt = 0;
        String monthStr = "";

        if (order.getPrice() != null) {
            totalAmount = order.getPrice().toString();
        }
        model.addAttribute("transactionAmount", totalAmount);

        ArrayList<String> errors = new ArrayList<String>();

        try {
            // Empty Field
            if (command.getCardname().length() == 0) {
                errors.add("First Name Required.");
                System.out.println("First Name Required.");
                valid = false;
            }
            // Empty Field
            if (command.getCardnumber().length() == 0) {
                errors.add("Credit Card Number Required.");
                System.out.println("Credit Card Number Required.");
                valid = false;
            }
            // Empty Field
            if (command.getExpmonth().length() == 0) {
                errors.add("Expiration Month Required.");
                System.out.println("Expiration Month Required.");
                valid = false;
            }
            // Empty Field
            if (command.getExpyear().length() == 0) {
                errors.add("Credit Card Expiration Year Required.");
                System.out.println("Credit Card Expiration Year Required.");
                valid = false;
            }
            // Empty Field
            if (command.getCvv().length() == 0) {
                errors.add("Credit Card CVV Required.");
                System.out.println("Credit Card CVV Required.");
                valid = false;
            }

            // Check if card format is valid
            String cardRegex = "^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35{3}){11})$";
            Pattern cardPattern = Pattern.compile(cardRegex);
            String cardNumber = command.getCardnumber().replace("-", "");
            Matcher cardMatcher = cardPattern.matcher(cardNumber);
            if (cardMatcher.matches()) {
                valid = true;
            } else {
                valid = false;
                errors.add("Invalid Card Number.");
                model.addAttribute("message", "Invalid Card Number.");
                System.out.println("Invalid Card Number.");
            }
            // Expiration year is not numeric or more than to length 4
            if (!isNumeric(command.getExpyear()) || command.getExpyear().length() > 4) {
                errors.add("Invalid Card Expration Year.");
                System.out.println("Invalid Card Expration Year.");
                valid = false;
            }
            // CCV is not numeric or more than to length 3
            if (!isNumeric(command.getCvv()) || command.getCvv().length() > 3) {
                errors.add("Invalid Card CVV.");
                model.addAttribute("message", "Invalid Card Number.");
                valid = false;
            }
            // Check if month is valid
            try {

                Month month = Month.valueOf(command.getExpmonth().toUpperCase());
                monthInt = Month.valueOf(command.getExpmonth().toUpperCase()).getValue();
                if (String.valueOf(monthInt).length() == 1) {
                    monthStr += "0" + String.valueOf(monthInt);
                } else {
                    monthStr = String.valueOf(monthInt);
                }
            } catch (IllegalArgumentException e) {
                // Invalid month
                errors.add("Invalid Card Expiration Month.");
                valid = false;
            }

            try {

                if (command != null && valid) {
                    Random r = new Random();
                    String orderNumber = String.valueOf(r.nextInt(99999 - 10000) + 10000);
                    PaymentCard card = new PaymentCard(command.getCardnumber(), command.getCardname(),
                            command.getExpmonth(), command.getExpyear(), command.getCvv());
                    model.addAttribute("message", "Thank Your for Your Payment. Your Order Number: " + orderNumber);
                    repository.save(card);
                    ////
                    System.out.println("===== CYBERSOURCE PAYMENT TEST =====");

                    if (DEBUG) {
                        System.out.println(apiHost);
                        System.out.println(merchantKeyId);
                        System.out.println(merchantsecretKey);
                        System.out.println(merchantId);
                    }
                    CyberSourceAPI api = new CyberSourceAPI();
                    CyberSourceAPI.setHost(apiHost);
                    CyberSourceAPI.setKey(merchantKeyId);
                    CyberSourceAPI.setSecret(merchantsecretKey);
                    CyberSourceAPI.setMerchant(merchantId);

                    AuthRequest auth = new AuthRequest();

                    String[] name = command.getCardname().split(" ");
                    auth.reference = "Order Number: " + orderNumber;
                    auth.billToFirstName = name[0];
                    auth.billToLastName = name[1];
                    auth.billToAddress = "N/A";
                    auth.billToCity = "N/A";
                    auth.billToState = "N/A";
                    auth.billToZipCode = "N/A";
                    auth.billToPhone = "N/A";
                    auth.billToEmail = "N/A";
                    auth.transactionAmount = "33.00";
                    auth.transactionCurrency = "USD";
                    auth.cardNumber = command.getCardnumber();
                    auth.cardExpMonth = monthStr;
                    auth.cardExpYear = command.getExpyear();
                    auth.cardCVV = command.getCvv();
                    auth.cardType = "001";
                    boolean authValid = false;
                    AuthResponse authResponse = new AuthResponse();
                    System.out.println("\n\nAuth Request: " + auth.toJson());
                    authResponse = api.authorize(auth);
                    System.out.println("\n\nAuth Response: " + authResponse.toJson());
                    if (authResponse.status.equals("AUTHORIZED")) {
                        authValid = true;
                    }

                    boolean captureValid = false;
                    CaptureRequest capture = new CaptureRequest();
                    CaptureResponse captureResponse = new CaptureResponse();
                    if (authValid) {
                        capture.reference = "Order Number: " + orderNumber;
                        capture.paymentId = authResponse.id;
                        capture.transactionAmount = "33.00";
                        capture.transactionCurrency = "USD";
                        System.out.println("\n\nCapture Request: " + capture.toJson());
                        captureResponse = api.capture(capture);
                        System.out.println("\n\nCapture Response: " + captureResponse.toJson());
                        if (captureResponse.status.equals("PENDING")) {
                            captureValid = true;
                        }

                    }

                    RefundRequest refund = new RefundRequest();
                    RefundResponse refundResponse = new RefundResponse();
                    boolean refundValid = false;

                    if (captureValid) {
                        refund.reference = "Order Number: " + orderNumber;
                        refund.captureId = captureResponse.id;
                        refund.transactionAmount = "33.00";
                        refund.transactionCurrency = "USD";
                        System.out.println("\n\nRefund Request: " + refund.toJson());
                        refundResponse = api.refund(refund);
                        System.out.println("\n\nRefund Response: " + refundResponse.toJson());
                        if (refundResponse.status.equals("PENDING")) {
                            refundValid = true;
                        }
                    }
                    return "addpayment";
                }
            } catch (IllegalArgumentException e) {
                return "addpayment";
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("message", "Validation Errors, Please Resubmit.");
            model.addAttribute("errors", errors);
            return "addpayment";
        }
        return "addpayment";
    }
}
*/