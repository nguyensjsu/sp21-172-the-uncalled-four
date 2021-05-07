package com.example.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;

import java.sql.Connection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.client.RestTemplate;

import com.example.model.Drink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Controller
@RequestMapping("/")
public class StarbucksMachineController {

  // Get requests

  private final String apiGetCard = "http://localhost:8090/card/{num}";
  private final String apiGetOrder = "http://localhost:8090/order/register/{regid}";
  private final String apiGetAllOrders = "http://localhost:8090/orders";


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

  @ModelAttribute
  public void addDrinksToModelDB(Model model){


    try{
      Connection connection = startConnection();
    }
  }

  public Connection startConnection() throws Exception {
    try{
      connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/starbucks", "athh", "cmpe172");
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return connection;
  }

  @ModelAttribute
  public void addDrinksToModel(Model model) {


    
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",  "Caffe Americano", 3.45, Drink.Type.BREWED_COFEE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",  "Blonde Roast", 5.45, Drink.Type.BREWED_COFEE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",  "Caffe Misto", 3.45, Drink.Type.BREWED_COFEE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Featured Starbuck Dark Roast Coffee", 3.45, Drink.Type.BREWED_COFEE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Pike Place Roast", 3.45, Drink.Type.BREWED_COFEE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Decaf Pike Place Roast", 3.45, Drink.Type.BREWED_COFEE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Capuccino", 3.45, Drink.Type.CAPUCCINO ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Espresso", 3.45, Drink.Type.EXPRESSO_SHOT ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Espresson Con Panna", 3.45, Drink.Type.EXPRESSO_SHOT ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Flat White", 3.45, Drink.Type.FLAT_WHITE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Honey Almondmilk Flat White", 3.45, Drink.Type.FLAT_WHITE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Pistachio Latte", 3.45, Drink.Type.LATTE) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Caffe Latte", 3.45, Drink.Type.LATTE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Cinnamon Dolce Latte", 3.45, Drink.Type.LATTE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Starbucks Reserve Latte", 3.45, Drink.Type.LATTE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Starbucks Reserve Hazelnut Bianco Latte", 3.45, Drink.Type.LATTE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Starbucks Blonde Vanilla Latte", 3.45, Drink.Type.LATTE ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Caramel Macchiato", 3.45, Drink.Type.MACCHIATO ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Espresso Macchiato", 3.45, Drink.Type.MACCHIATO );
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Caffe Mocha", 3.45, Drink.Type.MOCHA ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"Starbucks Reserve Dark Chocolate Mocha", 3.45, Drink.Type.MOCHA ) ;
    jdbcTemplate.update("insert into drink ( drink_name, price,  drink_type ) VALUES (?,?,?)",	"White Chocolate Mocha", 3.45, Drink.Type.MOCHA ) ;
  
  }

}
