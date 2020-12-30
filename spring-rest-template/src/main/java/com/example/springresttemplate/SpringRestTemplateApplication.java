package com.example.springresttemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.springresttemplate.model.Quote;

@SpringBootApplication
public class SpringRestTemplateApplication {
	
    // A logger, to send output to the log (the console, in this example).
	private static final Logger log = LoggerFactory.getLogger(SpringRestTemplateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringRestTemplateApplication.class, args);
	}
	
	// A RestTemplate, which uses the Jackson JSON processing library to process the incoming data.
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// A CommandLineRunner that runs the RestTemplate (and, consequently, fetches our quotation) on startup. 
	
    /**
     *
        If you request that URL through a web browser or curl, you receive a JSON document that looks something like this:

        {
           type: "success",
           value: {
              id: 10,
              quote: "Really loving Spring Boot, makes stand alone Spring apps easy."
           }
        } 

     * 	
     */
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
	// Start the application using "./gradle bootrun".
	// We should see the log statement 
	// (that represents the string representation of the object that is fetched from the given endpoint url) 
	// in the console at application start-up.

}
