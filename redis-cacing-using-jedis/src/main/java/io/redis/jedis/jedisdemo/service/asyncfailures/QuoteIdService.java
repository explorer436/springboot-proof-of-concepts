package io.redis.jedis.jedisdemo.service.asyncfailures;

import java.util.Set;

public interface QuoteIdService {
	
	// Set
	void addToQuoteIdSet(String quoteId);

	Set<String> getQuoteIdSet();
			
	boolean isQuoteIdSetMember(String quoteId);
	
	void removeQuoteIdFromSet(String quoteId);
		
}