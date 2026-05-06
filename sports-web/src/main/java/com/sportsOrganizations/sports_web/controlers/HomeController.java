package com.sportsOrganizations.sports_web.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sports")
public class HomeController {
  @GetMapping("/home")
  public String homePage(){
    return "index.html";
  }
   
}
