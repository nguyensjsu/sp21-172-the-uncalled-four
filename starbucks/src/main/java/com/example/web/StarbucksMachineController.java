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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.model.Drink;
import com.example.model.DrinkRepository;
import com.example.model.Order;
import com.example.model.Question;
import com.example.model.Drink.Type;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/")
public class StarbucksMachineController {

  @Autowired
  private DrinkRepository drinkRepository;

  private List<Integer> drinkIDs = new ArrayList<Integer>();

  private static void getAllCards() {
    final String apiGetAllCards = "http://localhost:8090/cards";

    //RestTemplate restTemplate = new RestTemplate();
   // String result = restTemplate.getForObject(apiGetAllCards, String.class);
   // System.out.println(result);
  }

  @GetMapping
  public String getWelcomePage(StartbucksCommand command, HttpSession session, Model model) {

  //  getAllCards();

    return "welcomepage";
  }
    // tag::filterByType[]
    private List<Drink> filterByType(List<Drink> ingredients, Type type) {
      return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
    }

  @GetMapping("/menu")
  public String getMenu(StartbucksCommand command, HttpSession session, Model model) {

    model.addAttribute("design", new Order());

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

  @PostMapping("/order")
  public String postAction(@Valid @ModelAttribute("order") Order order,
      @RequestParam(value = "action", required = true) String action, Errors errors, Model model,
      HttpServletRequest request) {
      
      List<String> orderInfo = new ArrayList<String>();
      orderInfo.add(order.getPrice());
      System.out.println(order.getPrice());
        
    return "menu";
  }

  @PostMapping("/question")
  public String postQuestion(@Valid @ModelAttribute("question") Question question,
      @RequestParam(value = "action", required = true) String action, Errors errors, Model model,
      HttpServletRequest request) throws InterruptedException, IOException {

    File dir = new File("/Users/guillerdalit/Desktop/Workplace/github/sp21-172-the-uncalled-four/backofficerabbitmq");

    question.setQuestion(question.getQuestion().replace(" ", "-"));
    System.out.println(question.getQuestion());
    String command = "make send env=dev question=";
    command = command.concat(question.getQuestion());

    Process proc = Runtime.getRuntime().exec(command, null, dir);
    System.out.println("sending question to rabbitmq localhost...");
    System.out.println(proc.waitFor());

    return "menu";
  }

  public void insertDrinks(String drink_name, double price, Type drink_type)
      throws IOException, InterruptedException {

    Drink drink = new Drink(drink_name, price, drink_type);
    drinkRepository.save(drink);
  }

  public void displayOrder(String id){
    String drinkID = id;
    
  }

  @ModelAttribute
  public void addDrinksToModel(Model model) throws IOException, InterruptedException {

    drinkRepository.deleteAllInBatch();
    insertDrinks("Caffe Americano", 3.42, Drink.Type.BREWED_COFEE);
    insertDrinks("Blonde Roast", 5.35, Drink.Type.BREWED_COFEE);
    insertDrinks("Caffe Misto", 3.55, Drink.Type.BREWED_COFEE);
    insertDrinks("Featured Starbuck Dark Roast Coffee", 3.45, Drink.Type.BREWED_COFEE);
    insertDrinks("Pike Place Roast", 3.45, Drink.Type.BREWED_COFEE);
    insertDrinks("Decaf Pike Place Roast", 3.45, Drink.Type.BREWED_COFEE);
    insertDrinks("Capuccino", 3.45, Drink.Type.CAPUCCINO);
    insertDrinks("Espresso", 3.45, Drink.Type.EXPRESSO_SHOT);
    insertDrinks("Espresson Con Panna", 3.45, Drink.Type.EXPRESSO_SHOT);
    insertDrinks("Flat White", 3.45, Drink.Type.FLAT_WHITE);
    insertDrinks("Honey Almondmilk Flat White", 3.45, Drink.Type.FLAT_WHITE);
    insertDrinks("Pistachio Latte", 3.45, Drink.Type.LATTE);
    insertDrinks("Caffe Latte", 3.45, Drink.Type.LATTE);
    insertDrinks("Cinnamon Dolce Latte", 3.45, Drink.Type.LATTE);
    insertDrinks("Starbucks Reserve Latte", 3.45, Drink.Type.LATTE);
    insertDrinks("Starbucks Reserve Hazelnut Bianco Latte", 3.45, Drink.Type.LATTE);
    insertDrinks("Starbucks Blonde Vanilla Latte", 3.45, Drink.Type.LATTE);
    insertDrinks("Caramel Macchiato", 3.45, Drink.Type.MACCHIATO);
    insertDrinks("Espresso Macchiato", 3.45, Drink.Type.MACCHIATO);
    insertDrinks("Caffe Mocha", 3.45, Drink.Type.MOCHA);
    insertDrinks("Starbucks Reserve Dark Chocolate Mocha", 3.45, Drink.Type.MOCHA);
    insertDrinks("White Chocolate Mocha", 3.45, Drink.Type.MOCHA);


    List<Drink> drinks = (List<Drink>) drinkRepository.findAll();
    Type[] types = Drink.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(drinks, type));
    }
  }

}

/*
  /*List<Drink> drinks;
   
    drinks = (List<Drink>) drinkRepository.findAll();

    List<String> brewedCoffees = new ArrayList<String>();
    List<String> capuccinos = new ArrayList<String>();
    List<String> expressoshots = new ArrayList<String>();
    List<String> flatwhites = new ArrayList<String>();
    List<String> macchiatos = new ArrayList<String>();
    List<String> mochas = new ArrayList<String>();

    List<Double> brewedCoffeePrices = new ArrayList<Double>();
    List<Double> capuccinosPrices = new ArrayList<Double>();
    List<Double> expressoshotsPrices = new ArrayList<Double>();
    List<Double> flatwhitesPrices = new ArrayList<Double>();
    List<Double> macchiatosPrices = new ArrayList<Double>();
    List<Double> mochasPrices = new ArrayList<Double>();

    int index = 0;
    for (Drink drink : drinks) {

      drinkIDs.add(index);

      if (drink.getType().equals(Drink.Type.BREWED_COFEE)) {
        brewedCoffees.add(drink.getDrink_name());
        brewedCoffeePrices.add(drink.getPrice());
      }
      if (drink.getType().equals(Drink.Type.CAPUCCINO)) {
        capuccinos.add(drink.getDrink_name());
        capuccinosPrices.add(drink.getPrice());
      }
      if (drink.getType().equals(Drink.Type.EXPRESSO_SHOT)) {
        expressoshots.add(drink.getDrink_name());
        expressoshotsPrices.add(drink.getPrice());
      }
      if (drink.getType().equals(Drink.Type.FLAT_WHITE)) {
        flatwhites.add(drink.getDrink_name());
        flatwhitesPrices.add(drink.getPrice());
      }
      if (drink.getType().equals(Drink.Type.MACCHIATO)) {
        macchiatos.add(drink.getDrink_name());
        macchiatosPrices.add(drink.getPrice());
      }
      if (drink.getType().equals(Drink.Type.MOCHA)) {
        mochas.add(drink.getDrink_name());
        mochasPrices.add(drink.getPrice());
      }

      index++;
    }

    model.addAttribute("brewedCoffees", brewedCoffees);//
    model.addAttribute("capuccinos", capuccinos); //
    model.addAttribute("expressoshots", expressoshots);//
    model.addAttribute("flatwhites", flatwhites);//
    model.addAttribute("macchiatos", macchiatos); //
    model.addAttribute("mochas", mochas); //

    model.addAttribute("brewedCoffeePrices", brewedCoffeePrices);//
    model.addAttribute("capuccinosPrices", capuccinosPrices); //
    model.addAttribute("expressoshotsPrices", expressoshotsPrices);//
    model.addAttribute("flatwhitesPrices", flatwhitesPrices);//
    model.addAttribute("macchiatosPrices", macchiatosPrices); //
    model.addAttribute("mochasPrices", mochasPrices); //

    Type[] types = Drink.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(drinks, type));
    }

    for (int id : drinkIDs) {
      System.out.println("ID " + id);
    }
*/

