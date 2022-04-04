package com.example.travel.services.impl;

import com.example.travel.dto.OrderDTO;
import com.example.travel.entity.TourOrder;
import com.example.travel.mapper.TourOrderMapper;
import com.example.travel.repository.ITourOrderRepository;
import com.example.travel.services.ITourOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TourOrderService implements ITourOrderService {

    @Autowired
    private ITourOrderRepository iTourOrderRepository;

    @Override
    public List<TourOrder> getAllTourOrder() {
        return iTourOrderRepository.findAll();
    }

    TourOrderMapper mapper = new TourOrderMapper();

    @Override
    @Transactional
    public void createTourOrder(OrderDTO createForm) {
        TourOrder tourOrder = mapper.toEntity(createForm);
        iTourOrderRepository.save(tourOrder);

    }
}
