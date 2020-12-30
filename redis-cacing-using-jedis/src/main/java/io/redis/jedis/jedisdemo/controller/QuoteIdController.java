package io.redis.jedis.jedisdemo.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.redis.jedis.jedisdemo.service.asyncfailures.QuoteIdService;

@RestController
public class QuoteIdController {
	
	@Autowired
	private QuoteIdService quoteIdService;
	
	@RequestMapping(value = "/quoteid",
			produces = { "application/json" }, 
			method = RequestMethod.POST)
	public void addToQuoteIds(@RequestBody String quoteId) {
		System.out.println(">>> QuoteIdController.addToQuoteIds()");
		System.out.println("quoteId : " + quoteId);
		
		quoteIdService.addToQuoteIdSet(quoteId);
			  
		System.out.println("<<< QuoteIdController.addToQuoteIds");
	}
	
	@RequestMapping(value = "/quoteids",
			produces = { "application/json" }, 
			method = RequestMethod.GET)
	public ResponseEntity getQuoteIdSet() {
		System.out.println(">>> QuoteIdController.getQuoteIdSet()");
		
		Set<String> result = quoteIdService.getQuoteIdSet();
			  
		System.out.println("<<< QuoteIdController.getQuoteIdSet");
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/isSetMember",
			produces = { "application/json" }, 
			method = RequestMethod.POST)
	public ResponseEntity isQuoteIdSetMember(@RequestBody String quoteId) {
		System.out.println(">>> QuoteIdController.isQuoteIdSetMember()");
		System.out.println("quoteId : " + quoteId);

		boolean result = quoteIdService.isQuoteIdSetMember(quoteId);

		System.out.println("<<< QuoteIdController.isQuoteIdSetMember");
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/removeQuoteIdFromSet",
			produces = { "application/json" }, 
			method = RequestMethod.POST)
	public void removeQuoteIdFromSet(@RequestBody String quoteId) {
		System.out.println(">>> QuoteIdController.removeQuoteIdFromSet()");
		System.out.println("quoteId : " + quoteId);

		quoteIdService.removeQuoteIdFromSet(quoteId);

		System.out.println("<<< QuoteIdController.removeQuoteIdFromSet");
	}
}
