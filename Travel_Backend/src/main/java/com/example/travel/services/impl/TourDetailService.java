package com.example.travel.services.impl;

import com.example.travel.dto.TourDetailDTO;
import com.example.travel.entity.*;
import com.example.travel.filter.TourFilter;
import com.example.travel.mapper.TourDetailMapper;
import com.example.travel.repository.IImageRepository;
import com.example.travel.repository.ITourDetailRepository;
import com.example.travel.services.ITourDetailService;
import com.example.travel.specification.TourSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class TourDetailService implements ITourDetailService {


    @Autowired
    private ITourDetailRepository iTourDetailRepository;

    @Autowired
    private IImageRepository iImageRepository;

    public TourDetailMapper mapper = new TourDetailMapper();



    @Override
    public List<TourDetail> getAllTours() {
        return iTourDetailRepository.findAll();

    }

    @Override
    public List<TourDetail> getSearchAllToursByLocationAndDateAndPrice(String title, Date dayStart, Long price) {
        return iTourDetailRepository.getTourDetailByTitleOrDayStartOrPrice(title, dayStart, price);
    }

    @Override
    public Page<TourDetail> getAllTourFilterSearch(Pageable pageable, TourFilter filter) throws ParseException {
        Specification<TourDetail> where = null;

        if(filter != null && filter.getDestination() != null && filter.getStartDate() != null){
            TourSpecification destinationSpecification = new TourSpecification("title", "LIKE", filter.getDestination());
            TourSpecification startDateSpecification = new TourSpecification("dayStart", "=", filter.getStartDate());
            where = Specification.where(destinationSpecification).and(startDateSpecification);

        }


        if(filter != null && filter.getPriceFrom() != null){
            TourSpecification priceFromSpecification = new TourSpecification("price", "Price>=", filter.getPriceFrom());
            if(where == null) {
                where = Specification.where(priceFromSpecification);
            }else {
                where = where.and(priceFromSpecification);
            }
        }
        if(filter != null && filter.getPriceTo() != null){
            TourSpecification priceToSpecification = new TourSpecification("price", "Price<=", filter.getPriceTo());
            if(where == null) {
                where = Specification.where(priceToSpecification);
            }else {
                where = where.and(priceToSpecification);
            }
        }
        if(filter != null && filter.getReview() != null){
            TourSpecification ReviewSpecification = new TourSpecification("star", "Review=", filter.getReview());
            if(where == null) {
                where = Specification.where(ReviewSpecification);
            }else {
                where = where.and(ReviewSpecification);
            }
        }
        return iTourDetailRepository.findAll(where,pageable);
    }

    @Override
    public TourDetail getTourDetailById(int id) {
        return iTourDetailRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void createTour(TourDetailDTO tourCreateForm) {

        TourDetail tourDetail = mapper.toEntity(tourCreateForm);
        //save bang cha lai
        TourDetail dataTourSaved = iTourDetailRepository.save(tourDetail);
        for (int i = 0; i < tourCreateForm.getImageList().size(); i++) {
            Image image = new Image();
            image = tourCreateForm.getImageList().get(i);
            image.setIdTourDetail(dataTourSaved);
            iImageRepository.save(image);
//            dataTourSaved.setImageList(tourCreateForm.getImageList());
        }


    }

    @Override
    @Transactional
    public void updateTour(int id, TourDetailDTO tourUpdateForm) {
        TourDetail tourDetail = getTourDetailById(id);
        if (tourDetail != null){
            tourDetail = mapper.toEntity(tourUpdateForm);
            iTourDetailRepository.save(tourDetail);
        }

    }

    @Override
    public void deleteTour(int id) {
        iTourDetailRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteTours(List<Integer> ids) {
        iTourDetailRepository.deleteTourDetailByIds(ids);
    }
}
