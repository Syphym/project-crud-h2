package ph.syphym.evergreen.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(){
        ConcurrentMapCacheManager manager = new ConcurrentMapCacheManager();
        manager.setAllowNullValues(false);
        manager.setCacheNames(Arrays.asList("GET_PRODUCT_BY_ORDER", "GET_PRODUCT_BY_ID", "GET_PRODUCT_BY_CRITERIA"));
        return manager;
    }

    @CacheEvict(value = {"GET_PRODUCT_BY_ORDER", "GET_PRODUCT_BY_ID", "GET_PRODUCT_BY_CRITERIA"}, allEntries = true)
    @Scheduled(fixedDelay = 300000, initialDelay = 0)
    public void evictProductCache() {
        System.out.println("Evicting Product Cache");
    }
}