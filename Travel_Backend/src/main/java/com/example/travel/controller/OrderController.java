package com.example.travel.controller;

import com.example.travel.dto.OrderDTO;
import com.example.travel.dto.TourDetailDTO;
import com.example.travel.entity.TourDetail;
import com.example.travel.entity.TourOrder;
import com.example.travel.mapper.TourOrderMapper;
import com.example.travel.services.ITourOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/tour-order")
@CrossOrigin("*")
@ControllerAdvice
public class OrderController {

    @Autowired
    private ITourOrderService iTourOrderService;

    TourOrderMapper mapper = new TourOrderMapper();

    @GetMapping
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Manager')")
    public ResponseEntity<List<OrderDTO>> getAllOrder() {
        List<TourOrder> tourOrderList = iTourOrderService.getAllTourOrder();
        List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
        for (TourOrder entity : tourOrderList) {
            orderDTOList.add(mapper.toDto(entity));
        }
        return new ResponseEntity<>(orderDTOList, HttpStatus.OK);
    }

    @PostMapping("/create-tour")
    @PreAuthorize("hasAuthority('Admin') or hasAuthority('Manager') or hasAuthority('User')")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO createForm){
        iTourOrderService.createTourOrder(createForm);
        return new ResponseEntity<String>("Created !", HttpStatus.OK);
    }
}

