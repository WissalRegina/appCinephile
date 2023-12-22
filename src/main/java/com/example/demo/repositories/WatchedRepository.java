package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.WatchedList;
import com.example.demo.entities.WatchedCle;

public interface WatchedRepository extends JpaRepository<WatchedList, WatchedCle>{
	
}
