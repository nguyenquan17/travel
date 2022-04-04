package com.example.travel.form;

import com.example.travel.dto.UserDTO;
import com.example.travel.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourUpdateForm {
    private String title;
    private String description;
    private String schedule;
    private Date dayStart;
    private String vehicle;
    private String departureFrom;
    private Long price;
    private int quantity;
    private String notes;

    private UserDTO modifiedBy;
    //image
    private List<Image> imageList;
    private int tourType;
    private int regional;
    private int tour;
}
