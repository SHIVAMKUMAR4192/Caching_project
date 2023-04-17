package com.cachingproject.h2repository;

import com.cachingproject.Founder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2repository extends JpaRepository<Founder,Integer> {
}