package com.example.web;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.client.RestTemplate;

import com.example.model.Drink;
import com.example.model.DrinkRepository;
import com.example.model.Question; 

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/")
public class StarbucksMachineController {

  @Autowired
  private DrinkRepository drinkRepository;

  private static void getAllCards() {
    final String apiGetAllCards = "http://localhost:8090/cards";

    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(apiGetAllCards, String.class);
    System.out.println(result);
  }

  @GetMapping
  public String getWelcomePage(StartbucksCommand command, HttpSession session, Model model) {

    getAllCards();
    return "welcomepage";
  }

  @GetMapping("/menu")
  public String getMenu(StartbucksCommand command, HttpSession session, Model model) {
    return "menu";
  }

  @GetMapping("/starbucks")
  public String getOrder(StartbucksCommand command, HttpSession session) {
    getAllCards();
    return "starbucks";
  }

  @GetMapping("/starbucksorder")
  public String placeOrder(StartbucksCommand command, HttpSession session) {
    return "starbucksorder";
  }

  @GetMapping("/starbuckscard")
  public String getStackbucksCard(StartbucksCommand command, HttpSession session) {
    getAllCards();
    return "starbuckscard";
  }

  @GetMapping("/starbucksreward")
  public String getStackbucksReward(StartbucksCommand command, HttpSession session) {
    getAllCards();
    return "starbucksreward";
  }

  @PostMapping
  public String postAction(@Valid @ModelAttribute("command") StartbucksCommand command,
      @RequestParam(value = "action", required = true) String action, Errors errors, Model model,
      HttpServletRequest request) {
    return "welcomepage";
  }
  @PostMapping("/question")
  public String postQuestion(@Valid @ModelAttribute("question") Question question,
      @RequestParam(value = "action", required = true) String action, Errors errors, Model model,
      HttpServletRequest request) throws InterruptedException, IOException  {

      File dir = new File ("/Users/guillerdalit/Desktop/Workplace/github/sp21-172-the-uncalled-four/backofficerabbitmq") ;
      String command = "make jar"; 
      Process proc = Runtime.getRuntime().exec(command,null,dir);
      System.out.println("sending question...");
      System.out.println(proc.waitFor());

      command = "make send env=dev"; 
      proc = Runtime.getRuntime().exec(command,null,dir);
      System.out.println("sending question to rabbitmq localhost...");
      System.out.println(proc.waitFor());
  
    return "menu";
  }

  public void insertDrinks(String drink_name, double price, String drink_type) throws IOException, InterruptedException {

    Drink drink = new Drink(drink_name, price, drink_type);
    drinkRepository.save(drink);
  }

  @ModelAttribute
  public void addDrinksToModel(Model model) throws IOException, InterruptedException {

    insertDrinks("Caffe Americano", 3.45, Drink.Type.BREWED_COFEE.name());
    insertDrinks("Blonde Roast", 5.45, Drink.Type.BREWED_COFEE.name());
    insertDrinks("Caffe Misto", 3.45, Drink.Type.BREWED_COFEE.name());
    insertDrinks("Featured Starbuck Dark Roast Coffee", 3.45, Drink.Type.BREWED_COFEE.name());
    insertDrinks("Pike Place Roast", 3.45, Drink.Type.BREWED_COFEE.name());
    insertDrinks("Decaf Pike Place Roast", 3.45, Drink.Type.BREWED_COFEE.name());
    insertDrinks("Capuccino", 3.45, Drink.Type.CAPUCCINO.name());
    insertDrinks("Espresso", 3.45, Drink.Type.EXPRESSO_SHOT.name());
    insertDrinks("Espresson Con Panna", 3.45, Drink.Type.EXPRESSO_SHOT.name());
    insertDrinks("Flat White", 3.45, Drink.Type.FLAT_WHITE.name());
    insertDrinks("Honey Almondmilk Flat White", 3.45, Drink.Type.FLAT_WHITE.name());
    insertDrinks("Pistachio Latte", 3.45, Drink.Type.LATTE.name());
    insertDrinks("Caffe Latte", 3.45, Drink.Type.LATTE.name());
    insertDrinks("Cinnamon Dolce Latte", 3.45, Drink.Type.LATTE.name());
    insertDrinks("Starbucks Reserve Latte", 3.45, Drink.Type.LATTE.name());
    insertDrinks("Starbucks Reserve Hazelnut Bianco Latte", 3.45, Drink.Type.LATTE.name());
    insertDrinks("Starbucks Blonde Vanilla Latte", 3.45, Drink.Type.LATTE.name());
    insertDrinks("Caramel Macchiato", 3.45, Drink.Type.MACCHIATO.name());
    insertDrinks("Espresso Macchiato", 3.45, Drink.Type.MACCHIATO.name());
    insertDrinks("Caffe Mocha", 3.45, Drink.Type.MOCHA.name());
    insertDrinks("Starbucks Reserve Dark Chocolate Mocha", 3.45, Drink.Type.MOCHA.name());
    insertDrinks("White Chocolate Mocha", 3.45, Drink.Type.MOCHA.name());

  }

}
