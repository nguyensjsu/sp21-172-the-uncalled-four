package com.example.starbucks.api;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
class StarbucksCardController{
    private final StarbucksCardRepository repository;

    StarbucksCardController (StarbucksCardRepository repository){
        this.repository = repository;
    }

    class Message{
        private String status;
        public String getStatus(){
            return status;
        }
        public void setStatus(String msg){
            status = msg;
        }
    }


@PostMapping("/cards")
StarbucksCard newCard(){
    StarbucksCard newcard = new StarbucksCard();
    Random random = new Random();
    int num = random.nextInt(900000000) + 100000000;
    int code = random.nextInt(900) * 100;

    newcard.setCardNumber(String.valueOf(num));
    newcard.setCardCode(String.valueOf(code));
    newcard.setBalance(20.00);
    newcard.setActivated(false);
    newcard.setStatus("New Card");
    return repository.save(newcard);
}

@GetMapping("/cards")
List<StarbucksCard> all(){
    return repository.findAll();
}

@DeleteMapping("/cards")
Message deleteAll(){
    repository.deleteAllInBatch();
    Message msg = new Message();
    msg.setStatus("All cards cleared");
    return msg;

}

@GetMapping("/cards/{num}")
StarbucksCard getOne(@PathVariable String num, HttpServletResponse response){
    StarbucksCard card = repository.findByCardNumber(num);
    if (card == null){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
    }
    return card;
    
}

@PostMapping("/cards/activate/{num}/{code}")
StarbucksCard activate (@PathVariable String num, @PathVariable String code, HttpServletResponse response){
StarbucksCard card = repository.findByCardNumber(num);
    if (card == null)
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error. Card Not Found!");
    if (card.getCardCode().equals(code)){
        card.setActivated(true);
        repository.save(card);
    } else{
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error, Card Not Valid!");
    }
    return card;
}

}