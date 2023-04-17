package com.cachingproject.service;

import com.cachingproject.Founder;
import com.cachingproject.repository.FounderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class FounderServiceTest {

    private FounderService founderService;

    @Mock
    private FounderRepository founderRepository;

    @BeforeEach
    void setUp() {
        this.founderService = new FounderService(this.founderRepository);
    }

    @Test
    void addFounder() {
        Founder founder = new Founder("Shiv","91social",123456,120);

        when(founderRepository.save(any(Founder.class))).thenReturn(founder);

        Founder savedFounder = founderService.addFounder(founder);

        assertThat(savedFounder.getFounderName()).isNotNull();
    }

    @Test
    void getFounderDetails() {
     Founder founder= new Founder("Sharath","SankalpIndia",324567,117);

     List<Founder> founderList = new ArrayList<>();
     founderList.add(founder);

     when(founderRepository.findAll()).thenReturn(founderList);

        List<Founder> founderDetailsFromService = founderService.getFounderDetails();

        assertThat(founderDetailsFromService.size()).isGreaterThan(0);


    }

    @Test
    void updateFounder() {
        Founder founder=new Founder(1,"Jitesh khatri","Jaecayy",65656565,1122);
        when(founderRepository.findById(1)).thenReturn(java.util.Optional.of(founder));
        when(founderRepository.save(any(Founder.class))).thenReturn(founder);
        founder.setFounderName("Gagan");
        Founder savedFounder=founderService.updateFounder(1,founder);
        assertThat(savedFounder.getFounderName());

    }

    @Test
    void deleteFounder() {
    }
}