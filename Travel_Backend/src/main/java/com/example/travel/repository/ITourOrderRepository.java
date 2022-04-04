package com.example.travel.repository;

import com.example.travel.entity.TourOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITourOrderRepository extends JpaRepository<TourOrder, Integer> {
}
