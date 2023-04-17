package com.cachingproject.service;

import com.cachingproject.Founder;
import com.cachingproject.repository.FounderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class FounderService {

    public FounderService(FounderRepository founderRepository) {
        this.founderRepository = founderRepository;
    }

    private static final Logger logger= LoggerFactory.getLogger(FounderService.class);

    @Autowired
    private FounderRepository founderRepository;

    public Founder addFounder(Founder founder){
        logger.info("founder creation with name - {} ",founder.getFounderName());
        return founderRepository.save(founder);
    }

    @Cacheable(cacheNames = "founder")
    public List<Founder> getFounderDetails() {
        logger.info(("Get founder details"));
        return founderRepository.findAll();
    }
    @CachePut(cacheNames = "founder",key = "#sNo")
    public Founder updateFounder(int sNo,Founder founder) {
        Founder existingFounder = founderRepository.findById(sNo).get();
        existingFounder.setFounderName(founder.getFounderName());
        existingFounder.setCompanyName(founder.getCompanyName());
        existingFounder.setCompanyRevenue(founder.getCompanyRevenue());
        logger.info("Student with roll no. -{} is updated successfully",sNo);
        return founderRepository.save(existingFounder);
    }
    @Scheduled(initialDelay = 60000,fixedRate = 60000)
    @CacheEvict(cacheNames = "founder",key = "#sno")
    public  void deleteFounder(@PathVariable int sno){
        founderRepository.deleteById(sno);


    }
}
