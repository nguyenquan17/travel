package com.example.travel.dto;

import com.example.travel.entity.Image;
import com.example.travel.entity.Regional;
import com.example.travel.entity.Tour;
import com.example.travel.entity.TourType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourDetailDTO {
    private int id;

    private int tourType;
    private int regional;
    private int tour;

    private String title;
    private String description;
    private String schedule;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayStart;
    private String vehicle;
    private String departureFrom;
    private Long price ;
    private int quantity;
    private String notes;
    private int star;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Image> imageList;

    private UserDTO creator;


    public TourDetailDTO(int id) {
        this.id = id;
    }
}
