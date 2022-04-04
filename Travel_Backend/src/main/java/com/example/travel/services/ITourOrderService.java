package com.example.travel.services;

import com.example.travel.dto.OrderDTO;
import com.example.travel.entity.TourOrder;

import java.util.List;

public interface ITourOrderService {
    List<TourOrder> getAllTourOrder();
    void createTourOrder(OrderDTO createForm);
}
