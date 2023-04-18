//package com.cachingproject.config;
//
//import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
//import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.beans.Customizer;
//
//@Configuration
//public class CacheConfig {
//
//    @Bean
//    CacheManagerCustomizer<ConcurrentMapCacheManager> customizer() {
//        return new Custom();
//    }
//
//
//    private class Custom implements CacheManagerCustomizer<ConcurrentMapCacheManager>{
//
//        @Override
//        public void customize(ConcurrentMapCacheManager cacheManager) {
//            cacheManager.setAllowNullValues(false);
//            cacheManager.setStoreByValue(true);
//        }
//    }
//
//}
