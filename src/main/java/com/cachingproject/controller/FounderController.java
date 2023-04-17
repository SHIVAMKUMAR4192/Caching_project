package com.cachingproject.controller;


import com.cachingproject.Founder;
import com.cachingproject.service.FounderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/Founder")
public class FounderController {

    @Autowired
    private FounderService founderService;



    @PostMapping(value="/Founder")
    public Founder founderCreation(@RequestBody Founder founder){
        return founderService.addFounder(founder);
    }
    @GetMapping(value = "/getFounderDetail")
    public List<Founder> getFounderDetail() {
        return founderService.getFounderDetails();
    }
    @PutMapping(value = "/updateFounder/{sno}")
    public Founder  updateFounder(@PathVariable("sno")int sno, @RequestBody Founder founder){
        return founderService.updateFounder(sno,founder);
    }
    @DeleteMapping(value = "/deleteFounder/{sno}")
    public void deleteFounder(@PathVariable("sno")int sno){
         founderService.deleteFounder(sno);
    }
}
