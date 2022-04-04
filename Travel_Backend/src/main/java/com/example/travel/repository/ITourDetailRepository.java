package com.example.travel.repository;

import com.example.travel.entity.TourDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ITourDetailRepository extends JpaRepository<TourDetail, Integer>, JpaSpecificationExecutor<TourDetail> {

    @Transactional
    @Modifying
    @Query(value = "	SELECT 	*	"
                 + "	FROM 	TourDetail"
                    + " WHERE 	title LIKE %:title% and price = :price or day_start = :dayStart ",nativeQuery = true)
    List<TourDetail> getTourDetailByTitleOrDayStartOrPrice(String title, Date dayStart, Long price);

//    List<TourDetail> getTourDetailByTitleContainingOrDayStartOrPrice(String title, Date dayStart, Long price);
    void deleteByIdIn(List<Integer> ids);

    @Modifying
    @Transactional
    @Query("DELETE FROM TourDetail where id in (:ids)")
    void deleteTourDetailByIds(List<Integer> ids);
}
