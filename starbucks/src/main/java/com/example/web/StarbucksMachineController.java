package com.example.web;

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

@Slf4j
@Controller
@RequestMapping("/")
public class StarbucksMachineController {

  // Get requests

  private final String apiGetCard = "http://localhost:8090/card/{num}";
  private final String apiGetOrder = "http://localhost:8090/order/register/{regid}";
  private final String apiGetAllOrders = "http://localhost:8090/orders";

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

    String[] espressos = { "Cappucino", "Caffe Latte", "Caffe Americano", "Espresso", "Espresso Machiato",
        "White Chocolate Mocha", "Caramel Macchiato" };
    String[] teas_and_coffees = {"Coffee", "Iced Coffee", "Hot Chocolate", "Iced Tea", "Iced Tea Lemonade", "Chai Tea Latte" };
    String[] frapuccinos  = {"Coffee",  "Caramel", "Mocha", "Java Chip", "Strawberries & Creme", "Vanilla Bean Creme", "Double Chocolatey Chip"};

    model.addAttribute("espressos", espressos);
    model.addAttribute("teas_and_coffees", teas_and_coffees);
    model.addAttribute("frapuccinos", frapuccinos);


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

}