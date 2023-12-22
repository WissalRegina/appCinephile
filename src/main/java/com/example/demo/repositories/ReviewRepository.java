package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.ReviewCle;
import com.example.demo.entities.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, ReviewCle>{
	//List<ReviewEntity> findAllById(ReviewCle r);
}
