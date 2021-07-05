package my.to.read.list.mybookstoread.controller;

import my.to.read.list.mybookstoread.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class BooksToReadController {
	
  /*@RequestMapping("/to-read")
    public Mono<String> toRead() {
      return WebClient.builder().build()
      .get().uri("http://localhost:8090/recommended").retrieve()
      .bodyToMono(String.class);
  }*/

  @Autowired
  private BookService bookService;

  @RequestMapping("/to-read")
  public Mono<String> toRead() {
    return bookService.readingList();
  }

}

