package com.cachingproject;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.cachingproject.h2repository.TestH2repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CachingProjectApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    public TestH2repository testH2repository;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate;


    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void urlform() {
        baseUrl = baseUrl.concat(":").concat(port + "/");
    }

    @Test
    public void testaddFounder() {
        Founder founder = new Founder(1, "Mahesh", "Founder of 91Social", 3232323, 10000);

        Founder resposeFounder = restTemplate.postForObject(baseUrl + "Founder", founder, Founder.class);
        assertEquals("Mahesh", resposeFounder.getFounderName());
        assertEquals(1, testH2repository.findAll().size());
    }
    @Test
    @Sql(statements = "DELETE FROM founder",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT  into founder(s_no,founder_name,company_name,company_revenue,no_of_employee_working) VALUES (1,'Jitesh khatri','MoonLight',897897,333)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from founder",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetFounderDetail() {
        List<Founder> founderList = restTemplate.getForObject(baseUrl+"getFounderDetail",List.class);
        assertEquals(1,founderList.size());
        assertEquals(1,testH2repository.findAll().size());
    }

    @Test
    @Sql(statements = "delete from founder",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT  into founder(s_no,founder_name,company_name,company_revenue,no_of_employee_working) VALUES (1,'Saurav','Tcs',456777,234)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from founder",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateFounder(){
        Founder founder=new Founder( 1,"Saurav","Tcs",456,456);
        restTemplate.put(baseUrl+"/updateFounder/{sno}",founder,1);
        Founder founderfromdb = testH2repository.findAll().get(0);

        assertAll(
                ()->assertNotNull(founderfromdb),
                ()->assertEquals(456,founderfromdb.getCompanyRevenue())
        );
    }

    @Test
    @Sql(statements = "delete from founder",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "INSERT  into founder(s_no,founder_name,company_name,company_revenue,no_of_employee_working) VALUES (1,'Saurav','Tcs',45677,345)",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "delete from founder",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeleteFounder(){

        int recordCount  = testH2repository.findAll().size();
        Founder getFounder = testH2repository.findAll().get(0);
        assertEquals(1,recordCount);
        System.out.println(getFounder.getSNo());
        restTemplate.delete(baseUrl+"deleteFounder/{sno}",getFounder.getSNo());
        assertEquals(0,testH2repository.findAll().size());
    }
}
