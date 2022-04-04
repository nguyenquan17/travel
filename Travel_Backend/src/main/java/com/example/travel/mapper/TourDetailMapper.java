package com.example.travel.mapper;

import com.example.travel.dto.TourDetailDTO;
import com.example.travel.dto.UserDTO;
import com.example.travel.entity.*;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

public class TourDetailMapper {
    public TourDetailDTO toDto (@NotNull TourDetail entity){
        TourDetailDTO dto = new TourDetailDTO();

        dto.setTourType(entity.getTourType().getId());
        dto.setRegional(entity.getRegional().getId());
        dto.setTour(entity.getTourName().getId());
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setSchedule(entity.getSchedule());
        dto.setDayStart(entity.getDayStart());
        dto.setVehicle(entity.getVehicle());
        dto.setDepartureFrom(entity.getDepartureFrom());
        dto.setPrice(entity.getPrice());
        dto.setQuantity(entity.getQuantity());
        dto.setNotes(entity.getNotes());
        dto.setStar(entity.getStar());
        dto.setImageList( entity.getImageList()
                .stream()
                .map(image -> new Image(image.getId(), image.getImageUrl()))
                .collect(Collectors.toList()));
        dto.setCreator( new UserDTO(entity.getManager().getId(),entity.getManager().getFullName()));

        return dto;
    }

    public TourDetail toEntity(@NotNull TourDetailDTO dto){
        TourDetail entity = new TourDetail();
        entity.setId(dto.getId());
        entity.setTourType(new TourType(dto.getTourType()));
        entity.setRegional(new Regional(dto.getRegional()));
        entity.setTourName(new Tour(dto.getTour()));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setSchedule(dto.getSchedule());
        entity.setDayStart(dto.getDayStart());
        entity.setVehicle(dto.getVehicle());
        entity.setDepartureFrom(dto.getDepartureFrom());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setNotes(dto.getNotes());


        entity.setImageList(dto.getImageList()
                .stream()
                .map(image -> new Image(image.getId(), image.getImageUrl()))
                .collect(Collectors.toList()));

        entity.setManager(new User(dto.getCreator().getId(),dto.getCreator().getFullName()));

        return entity;
    }
}
