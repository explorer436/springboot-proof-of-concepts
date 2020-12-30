package io.redis.jedis.jedisdemo.configuration;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

	/*@Value("${redis.host}")
	private String host;
	
	@Value("${redis.port}")
	private int port;

	@Value("${redis.jedis.pool.max-total}")
	private int maxTotal;
	
	@Value("${redis.jedis.pool.max-idle}")
	private int maxIdle;
	
	@Value("${redis.jedis.pool.min-idle}")
	private int minIdle;
	
	@Value("${redis.jedis.pool.maxWaitMillis}")
	private int maxWaitMillis;*/
	
	@Override
	public CacheErrorHandler errorHandler() {
		// the methods don't have to do anything custom.
		// we just have to override the default operations so that the exceptions will not be thrown.
		return new CustomCacheErrorHandler();
	}

	// Create jedis client with pool configuration
	// https://commons.apache.org/proper/commons-pool/apidocs/org/apache/commons/pool2/impl/GenericObjectPool.html
	/*@Bean
	public JedisClientConfiguration getJedisClientConfiguration() {
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder JedisPoolingClientConfigurationBuilder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
				.builder();
		GenericObjectPoolConfig GenericObjectPoolConfig = new GenericObjectPoolConfig();
		GenericObjectPoolConfig.setMaxTotal(maxTotal);
		GenericObjectPoolConfig.setMaxIdle(maxIdle);
		GenericObjectPoolConfig.setMinIdle(minIdle);
		GenericObjectPoolConfig.setMaxWaitMillis(maxWaitMillis);
		return JedisPoolingClientConfigurationBuilder.poolConfig(GenericObjectPoolConfig).build();		
	}*/

	// create jedis connection factory
	/*@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);
		redisStandaloneConfiguration.setPort(port);
		return new JedisConnectionFactory(redisStandaloneConfiguration, getJedisClientConfiguration());
	}*/

	// create jedis template
	/* @Bean
	public RedisTemplate redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(getJedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}*/
	
	// RedisCacheConfiguration to provide our Redis default Configuration for places which are not managed by us.
	/*@Bean
    public RedisCacheConfiguration cacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig();
    }*/
	
	/*
	 * Define custom CacheConfigurations.
	 * The configuration gets created with the timeout specified in the Map cacheExpirations.
	 */
	@Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		
		 // Mapping of cacheNames to expira-after-write timeout in seconds
	    Map<String, Long> cacheExpirations = new HashMap<>();
	    // cacheExpirations.put("referenceDataCache", (long) 120);

        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        RedisCacheConfiguration redisCacheConfiguration = null;
        for (Entry<String, Long> cacheNameAndTimeout : cacheExpirations.entrySet()) {
        	redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(Duration.ofSeconds(cacheNameAndTimeout.getValue()));
            cacheConfigurations.put(cacheNameAndTimeout.getKey(), redisCacheConfiguration);
        }

        return RedisCacheManager
                .builder(redisConnectionFactory)
                // .cacheDefaults(cacheConfiguration())
                .withInitialCacheConfigurations(cacheConfigurations).build();
    }
	
	// define setOperations as a bean so that it will be available to the entire application
	@Bean
	@Qualifier("setOperations")
	public SetOperations<String, String> SetOperations(RedisTemplate<String, String> redisTemplate) {
		return redisTemplate.opsForSet();
	}

}
