package com.example.starbucks.api;

import java.util.List;
import java.util.HashMap;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.http.HttpStatus;



@RestController
public class StarbucksOrderController{
    private final StarbucksOrderRepository repository;


    @Autowired
    private StarbucksCardRepository cardRepository;

    class Message{
        private String status;
        public String getStatus(){
            return status;
        }
        public void setStatus(String msg){
            status = msg;
        }
    }

    private HashMap<String, StarbucksOrder> orders = new HashMap();

    public StarbucksOrderController(StarbucksOrderRepository repository){
        this.repository = repository;
    }

    @GetMapping("/orders")
    List<StarbucksOrder> all(){
        return repository.findAll();
    }

    @DeleteMapping("/orders")
    Message deleteAll(){
        repository.deleteAllInBatch();
        orders.clear();
        Message msg = new Message();
        msg.setStatus("All orders cleared");
        return msg;
    }

    @PostMapping("/order/register/{regid}")
    @ResponseStatus(HttpStatus.CREATED)
    StarbucksOrder newOrder(@PathVariable String regid, @RequestBody StarbucksOrder order){
        System.out.println("Placing order (Reg ID = " + regid +" ) => " + order);

        if (order.getDrink().equals("") || order.getMilk().equals("")||order.getSize().equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order Request");
        }
        StarbucksOrder active = orders.get(regid);
        if (active != null){
            System.out.println("Active Order (Reg ID = " + regid + ") => "+ active);
            if (active.getStatus().equals("Ready for Payment."))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order Exists");
        }

        double price = 0.0;
        String milk = "";
        switch (order.getDrink()){
            case "Caffe Latte":
                switch (order.getSize()){
                    case "Tall":
                        price = 2.85;
                        break;
                    case "Grande":
                        price = 3.65;
                        break;
                    case "Venti":
                        price = 2.95;
                        break;
                    case "Your Own Cup":
                        price = 3.95;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
                }

                break;
            case "Caffe Americano":
                switch (order.getSize()){
                    case "Tall":
                        price = 2.25;
                        break;
                    case "Grande":
                        price = 2.65;
                        break;
                    case "Venti":
                        price = 2.95;
                        break;
                    case "Your Own Cup":
                        price = 2.95;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");

                }

                break;
            case "Caffe Mocha":
            switch (order.getSize()){
                case "Tall":
                    price = 3.45;
                    break;
                case "Grande":
                    price = 4.15;
                    break;
                case "Venti":
                    price = 2.95;
                    break;
                case "Your Own Cup":
                    price = 4.45;
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");

            }

                break;
            case "Espresso":
            switch (order.getSize()){
                case "Short":
                    price = 1.75;
                    break;
                case "Tall":
                    price = 1.95;
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");

            }

                break;
            case "Capuchino":
            switch (order.getSize()){
                case "Tall":
                    price = 2.95;
                    break;
                case "Grande":
                    price = 3.65;
                    break;
                case "Venti":
                    price = 2.95;
                    break;
                case "Your Own Cup":
                    price = 3.95;
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");

            }

                break;

            default:
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Drink!");
        }
        double tax = 0.0725;
        double total = price + (price * tax);
        double scale= Math.pow(10,2);
        double rounded= Math.round(total * scale) / scale;
        order.setTotal(rounded);
        //save order
        order.setStatus("Ready for Payment.");
        StarbucksOrder new_order = repository.save(order);
        orders.put(regid,new_order);
        return new_order;
    }

    @GetMapping("/order/register/{regid}")
    StarbucksOrder getActiveOrder(@PathVariable String regid,HttpServletResponse response){
        StarbucksOrder active = orders.get(regid);
        if (active != null){
            return active;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found");

        }
    }
    @DeleteMapping("order/register/{regid}")
    Message deleteActiveOrder(@PathVariable String regid){
        StarbucksOrder active = orders.get(regid);
        if (active!= null){
            orders.remove(regid);
            Message msg = new Message();
            msg.setStatus("Active Order Cleared");
            return msg;
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
    }

    @PostMapping("order/register/{regid}/pay/{cardnum}")
    StarbucksCard processOrder(@PathVariable String regid, @PathVariable String cardnum){
        System.out.println("Pay for Order: Reg ID =" + regid+ "Using Card ="+ cardnum);
        StarbucksOrder active = orders.get(regid);
        if (active == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found");

        }
        if (cardnum.equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Number Not Provided");

        }
        if (active.getStatus().startsWith("Paid with Card")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clear Paid Actice Order!");
        }
        StarbucksCard card = cardRepository.findByCardNumber(cardnum);
        if(card == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Found!");
        }

        if (!card.isActivated()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Activated!");
        }

        double price = active.getTotal();
        double balance = card.getBalance();
        if (balance - price <0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Fund on Card.");

        }
        double new_balance = balance - price;
        card.setBalance(new_balance);
        String status ="Paid with Card " + cardnum + "Balance: $" + new_balance +".";
        active.setStatus(status);
        cardRepository.save(card);
        repository.save(active);
        return card;
    }
}
