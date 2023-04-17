package com.cachingproject.repository;

import com.cachingproject.Founder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FounderRepository extends JpaRepository<Founder,Integer> {

}
