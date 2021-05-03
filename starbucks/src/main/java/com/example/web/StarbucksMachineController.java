package com.example.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import com.example.starbucksmachine.StarbucksMachine;
import org.springframework.web.client.RestTemplate;

import com.example.model.Drink;
import com.example.model.Drink.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;


@Slf4j
@Controller
@RequestMapping("/")
public class StarbucksMachineController {

  // Get requests

  private final String apiGetCard = "http://localhost:8090/card/{num}";
  private final String apiGetOrder = "http://localhost:8090/order/register/{regid}";
  private final String apiGetAllOrders = "http://localhost:8090/orders";

  private List<Drink> filterByType(List<Drink> drinks, Type type) {
    return drinks.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());
  }

  @Autowired
  JdbcTemplate jdbcTemplate;

  // Post Requests

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
  @ModelAttribute
  public void addDrinksToModel(Model model) {

    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",  "Caffe Americano", 3.45, "Americano" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",  "Blonde Roast", 5.45, "BrewedCoffee" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",  "Caffe Misto", 3.45, "BrewedCoffee" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Featured Starbuck Dark Roast Coffee", 3.45, "BrewedCoffee" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Pike Place Roast", 3.45, "BrewedCoffee" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Decaf Pike Place Roast", 3.45, "BrewedCoffee" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Capuccino", 3.45, "Capuccino" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Espresso", 3.45, "EspressoShots" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Espresson Con Panna", 3.45, "EspressoShots" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Flat White", 3.45, "FlatWhite" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Honey Almondmilk Flat White", 3.45, "FlatWhite" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Pistachio Latte", 3.45, "Latte" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Caffe Latte", 3.45, "Latte" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Cinnamon Dolce Latte", 3.45, "Latte" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Starbucks Reserve Latte", 3.45, "Latte" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Starbucks Reserve Hazelnut Bianco Latte", 3.45, "Latte" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Starbucks Blonde Vanilla Latte", 3.45, "Latte" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Caramel Macchiato", 3.45, "Macchiato" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Espresso Macchiato", 3.45, "Macchiato" );
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Caffe Mocha", 3.45, "Mocha" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Starbucks Reserve Dark Chocolate Mocha", 3.45, "Mocha" ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"White Chocolate Mocha", 3.45, "Mocha" ) ;
  

    List<Drink> drinks = Arrays.asList(
  
    //  HOT_COFFEE, HOT_TEA, COLD_COFFEE, ICED_TEA, FRAPUCCINO,
        //Sample Drinks Frapfucinno
        new Drink("1", "Coffee", Type.FRAPUCCINO),
        new Drink("2", "Caramel", Type.FRAPUCCINO), 
        new Drink("3", "Mocha", Type.FRAPUCCINO),
        new Drink("4", "Java Chip", Type.FRAPUCCINO), 
        new Drink("5", "Strawberries & Creme", Type.FRAPUCCINO),
        new Drink("6", "Vanilla Bean Creme", Type.FRAPUCCINO), 
        new Drink("7", "Double Chocolatey Chip", Type.FRAPUCCINO),

        //Sample Drinks Iced Tea
        new Drink("8", "Iced Tea", Type.ICED_TEA),
        new Drink("9", "Iced Tea Lemonade", Type.ICED_TEA), 
        new Drink("10", "Iced Green Tea", Type.ICED_TEA), 

        //Sample Cold Coffee
        new Drink("11", "Nitro Cold Brew", Type.COLD_COFFEE),
        new Drink("12", "Iced Caffe Americano", Type.COLD_COFFEE), 
        new Drink("13", "Iced Coffee with Milk", Type.COLD_COFFEE),
        new Drink("14", "Iced Espresso", Type.COLD_COFFEE),
        new Drink("15", "Iced Shaken Espresso", Type.COLD_COFFEE),

        //Sample Drinks Hot Tea
        new Drink("16", "Chai Latte", Type.HOT_TEA),
        new Drink("17", "Chai Tea", Type.HOT_TEA), 
        new Drink("118", "Earl Grey Tea", Type.HOT_TEA),

        //Sample Hot Coffee
        new Drink("11", "Blonde Roast", Type.HOT_COFFEE),
        new Drink("12", "Caffe Misto", Type.HOT_COFFEE), 
        new Drink("13", "Capuccino", Type.HOT_COFFEE),
        new Drink("14", "Espresso", Type.HOT_COFFEE),
        new Drink("15", "Flat White", Type.HOT_COFFEE)
        
        );
    Type[] types = Drink.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(), filterByType(drinks, type));
    }
  }

}
