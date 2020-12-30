package io.redis.jedis.jedisdemo.service.asyncfailures;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteIdRepositoryImpl implements QuoteIdRepository{
	
	public static final String REDIS_SET_KEY  = "QuoteIdSet";
	
	@Autowired
	@Qualifier("setOperations")
	private SetOperations<String, String> setOps;
	
	//********** Set *************

	@Override
	public void addToQuoteIdSet(String... quoteIds) {
		setOps.add(REDIS_SET_KEY, quoteIds);
		
	}

	@Override
	public Set<String> getQuoteIdSet() {
		return setOps.members(REDIS_SET_KEY);
	}

	@Override
	public boolean isQuoteIdSetMember(String quoteId) {
		boolean result;
		System.out.println(">>> QuoteIdRepositoryImpl.isQuoteIdSetMember() - quoteId : " + quoteId);
		result = setOps.isMember(REDIS_SET_KEY, quoteId);
		System.out.println("<<< QuoteIdRepositoryImpl.isQuoteIdSetMember() - result : " + result);
		return result;
	}

	@Override
	public void removeQuoteIdFromSet(String quoteId) {
		setOps.remove(REDIS_SET_KEY, quoteId);
		
	}
}
