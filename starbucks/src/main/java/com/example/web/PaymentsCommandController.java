package com.example.web;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Optional;
import java.time.*;
import java.util.ArrayList;
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
                            @RequestParam(value="action", required=true) String action,
                            Errors errors, Model model, HttpServletRequest request) {
    
        log.info( "Action: " + action ) ;
        log.info( "Command: " + command ) ;

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
        auth.transactionAmount = "30.00";
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
            capture.transactionAmount= "30.00";
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
            command.setTransactionAmount("30.00");
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
     

        return "addpayment";
    
    }
}
