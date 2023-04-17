package com.cachingproject;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name="founder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Founder implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sNo;
    private String founderName;
    private String companyName;
    private Integer companyRevenue;
    private Integer noOfEmployeeWorking;

    public Founder(String founderName, String companyName, Integer companyRevenue, Integer noOfEmployeeWorking) {
        this.founderName = founderName;
        this.companyName = companyName;
        this.companyRevenue = companyRevenue;
        this.noOfEmployeeWorking = noOfEmployeeWorking;
    }
}
