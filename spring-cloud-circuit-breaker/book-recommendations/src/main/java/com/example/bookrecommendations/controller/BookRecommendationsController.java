package com.example.bookrecommendations.controller;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookRecommendationsController {
	
	
  @RequestMapping(value = "/recommended")
  public Mono<String> recommendedList(){
    return Mono.just("Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)");
  }

}
