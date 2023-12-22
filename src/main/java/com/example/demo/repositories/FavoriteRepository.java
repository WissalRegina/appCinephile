package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Favorite;
import com.example.demo.entities.FavoriteCle;

public interface FavoriteRepository extends JpaRepository<Favorite, FavoriteCle>{

}
