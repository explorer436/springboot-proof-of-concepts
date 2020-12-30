package io.redis.jedis.jedisdemo.service.asyncfailures;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteIdServiceImpl implements QuoteIdService {

	@Autowired
	QuoteIdRepository quoteIdRepository;

	@Override
	public void addToQuoteIdSet(String quoteId) {
		quoteIdRepository.addToQuoteIdSet(quoteId);		
	}

	@Override
	public Set<String> getQuoteIdSet() {
		return quoteIdRepository.getQuoteIdSet();
	}

	@Override
	public boolean isQuoteIdSetMember(String quoteId) {
		boolean result;
		System.out.println(">>> QuoteIdServiceImpl.isQuoteIdSetMember() - quoteId : " + quoteId);
		result = quoteIdRepository.isQuoteIdSetMember(quoteId);
		System.out.println("<<< QuoteIdServiceImpl.isQuoteIdSetMember() - result : " + result);
		return result;
	}

	@Override
	public void removeQuoteIdFromSet(String quoteId) {
		quoteIdRepository.removeQuoteIdFromSet(quoteId);
		
	}

}
