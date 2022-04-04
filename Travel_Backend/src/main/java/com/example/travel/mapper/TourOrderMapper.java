package com.example.travel.mapper;

import com.example.travel.dto.OrderDTO;
import com.example.travel.dto.TourDetailDTO;
import com.example.travel.dto.UserDTO;
import com.example.travel.entity.PrimaryKey.TourOrderPK;
import com.example.travel.entity.TourDetail;
import com.example.travel.entity.TourOrder;
import com.example.travel.entity.User;

public class TourOrderMapper {
    public OrderDTO toDto(TourOrder entity){
        OrderDTO dto = new OrderDTO();
        dto.setComposeId(new TourOrderPK(entity.getComposeId().getIdUser(),
                                        entity.getComposeId().getIdTourDetail()));
        dto.setId(entity.getId());
        dto.setNameOrder(entity.getNameOrder());
        dto.setDateOrder(entity.getDateOrder());
        dto.setCostOrder(entity.getCostOrder());
        dto.setNumberOfPeople(entity.getNumberOfPeople());
        dto.setNumberOfRooms(entity.getNumberOfRooms());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setTourDetail(new TourDetailDTO(entity.getTourDetail().getId()));
        dto.setCustomer(new UserDTO(entity.getCustomer().getId(),
                                    entity.getCustomer().getFullName(),
                                    entity.getCustomer().getEmail()));
        return dto;
    }

    public TourOrder toEntity(OrderDTO dto){
        TourOrder entity = new TourOrder();
        entity.setComposeId(new TourOrderPK(dto.getComposeId().getIdUser(),
                                            dto.getComposeId().getIdTourDetail()));
        entity.setId(dto.getId());
        entity.setNameOrder(dto.getNameOrder());
        entity.setDateOrder(dto.getDateOrder());
        entity.setCostOrder(dto.getCostOrder());
        entity.setNumberOfPeople(dto.getNumberOfPeople());
        entity.setNumberOfRooms(dto.getNumberOfRooms());
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setTourDetail(new TourDetail(dto.getTourDetail().getId()));
        entity.setCustomer(new User(dto.getCustomer().getId(),
                                    dto.getCustomer().getFullName(),
                                    dto.getCustomer().getEmail()));

        return entity;
    }
}
