package com.example.travel.services;

import com.example.travel.dto.TourDetailDTO;
import com.example.travel.entity.TourDetail;
import com.example.travel.filter.TourFilter;
import com.example.travel.form.TourCreateForm;
import com.example.travel.form.TourUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ITourDetailService {
    List<TourDetail> getAllTours();
    List<TourDetail> getSearchAllToursByLocationAndDateAndPrice(String title, Date dayStart, Long price);
    Page<TourDetail> getAllTourFilterSearch(Pageable pageable, TourFilter filter) throws ParseException;

    TourDetail getTourDetailById(int id);
    void createTour(TourDetailDTO tourCreateForm);
    void updateTour(int id, TourDetailDTO tourUpdateForm);
    void deleteTour(int id);
    void deleteTours(List<Integer> ids);
}
