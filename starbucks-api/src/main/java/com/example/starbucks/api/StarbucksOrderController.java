package com.example.starbucks.api;

import java.util.List;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@RestController
public class StarbucksOrderController{
    private final StarbucksOrderRepository repository;

    @Autowired
    private StarbucksCardRepository cardsRepository;

    class Message{
        private String status;

        public String getStatus(){
            return status;
        }

        public void setStatus(String msg){
            status = msg;
        }
    }

    private HashMap<String, StarbucksOrder> orders = new HashMap<>();

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
        msg.setStatus("Orders Cleared.");
        return msg;
    }

    @PostMapping("/order/register/{regid}")
    @ResponseStatus(HttpStatus.CREATED)
    StarbucksOrder newOrder(@PathVariable String regid, @RequestBody StarbucksOrder order) {
        System.out.println("Placing Order (Reg ID = " + regid + ") => " + order);
        if (order.getDrink().equals("") || order.getMilk().equals("") || order.getSize().equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order Request.");
        }

        StarbucksOrder active = orders.get(regid);
        if (active != null){
            System.out.println("Active Order (Reg ID = " + regid + ") => " + active);
            if (active.getStatus().equals("Ready for Payment.")){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order already exists.");
            }
        }

        double price = 0.0;
        switch (order.getDrink()){
            case "Caffe Latte":
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
                    case "Own Cup":
                        price = 3.95;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid size.");
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
                    case "Own Cup":
                        price = 2.95;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid size.");
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
                        price = 4.45;
                        break;
                    case "Own Cup":
                        price = 4.45;
                        break;
                    default:
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid size.");
                }
            break;
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid drink.");
        }
        double tax = 0.0725;
        double total = price + (price * tax);
        double scale = Math.pow(10,2);
        double rounded = Math.round(total * scale) / scale;
        order.setTotal(rounded);
        order.setStatus("Ready for payment.");
        StarbucksOrder new_order = repository.save(order);
        orders.put(regid, new_order);
        return new_order;
    }

    @GetMapping("/order/register/{regid}")
    StarbucksOrder getActiveOrder(@PathVariable String regid, HttpServletResponse response) {
        StarbucksOrder active = orders.get(regid);
        if(active != null){
            return active;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found.");
        }
    }

    @DeleteMapping("/order/register/{regid}")
    Message deleteActiveOrder(@PathVariable String regid){
        StarbucksOrder active = orders.get(regid);
        if(active != null){
            orders.remove(regid);
            Message msg = new Message();
            msg.setStatus("Active order cleared.");
            return msg;
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found.");   
        }
    }

    @PostMapping("/order/register/{regid}/pay/{cardnum}")
    StarbucksCard processOrder(@PathVariable String regid, @PathVariable String cardnum){
        System.out.println("Pay for Order: Reg ID = " + regid + " Using Card = " + cardnum);
        StarbucksOrder active = orders.get(regid);
        if (active == null){
            System.out.println("Order found check");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found.");
        }
        if (cardnum.equals("")){
            System.out.println("Card number check");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card number not provided.");
        }
        if (active.getStatus().startsWith("Paid with Card")){
            System.out.println("Already paid check");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clear paid active order.");
        }
        StarbucksCard card = cardsRepository.findByCardNumber(cardnum);
        if(card == null){
            System.out.println("Find card check");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not found.");
        }
        if (!card.isActivated()){
            System.out.println("Card activation check");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card not activated.");
        }
        double price = active.getTotal();
        double balance = card.getBalance();
        if (balance - price < 0){
            System.out.println("Poor man check");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds on card.");
        }
        double new_balance = balance - price;
        card.setBalance(new_balance);
        String status = "Paid with card: " + cardnum + " Balance: $" + new_balance + ".";
        active.setStatus(status);
        cardsRepository.save(card);
        repository.save (active);
        return card;
    }




}