package poc.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class CustomCacheConfiguration extends CachingConfigurerSupport {  

    //@Autowired RedisConnectionFactory redisConnectionFactory;

    @Override
    public CacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }

    /**
     * The Generic Jackson to JSon serializer works the first time but throws an error when trying to unserialzie from the cache. Causing a cache miss
     * and the caching layer defers to the service.
     * Could not read JSON: Could not resolve type id 'java.util.GregorianCalendar' as a subtype of [simple type, class javax.xml.datatype.XMLGregorianCalendar]
     * 
     * The OXM serializer (for XML) didnt' work as it needed a custom marsheller setup. Which seemed to be one per data type in the cache which could be expensive.
     */
//     @Override public CacheManager cacheManager()
//     {
//         SerializationPair<Object> jsonSerializer =  RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
//         RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(jsonSerializer);

//         return RedisCacheManager.RedisCacheManagerBuilder
//                 .fromConnectionFactory(redisConnectionFactory)
//                 .cacheDefaults( configuration )
//                 .build();

//     }
}