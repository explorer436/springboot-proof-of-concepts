package io.redis.jedis.jedisdemo.service.asyncfailures;

import java.util.Set;

public interface QuoteIdRepository {

	// Set
	void addToQuoteIdSet(String ... quoteIds);

	Set<String> getQuoteIdSet();
				
	boolean isQuoteIdSetMember(String quoteId);
	
	void removeQuoteIdFromSet(String quoteId);
}
