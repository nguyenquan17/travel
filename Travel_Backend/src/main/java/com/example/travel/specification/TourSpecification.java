package com.example.travel.specification;

import com.example.travel.entity.TourDetail;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@AllArgsConstructor
public class TourSpecification implements Specification<TourDetail> {
    private static final long serialVersionUId = 1L;
    private String field;
    private String operator;
    private Object value;


    @Override
    public Predicate toPredicate(Root<TourDetail> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(operator.equalsIgnoreCase("Price>=")){
            if(value instanceof Long){
                return criteriaBuilder.greaterThanOrEqualTo(root.get(field), (Long) value);
            }
        }
        if(operator.equalsIgnoreCase("Price<=")){
            if(value instanceof Long){
                return criteriaBuilder.lessThanOrEqualTo(root.get(field), (Long) value);
            }
        }
        if(operator.equalsIgnoreCase("Review=")){
            if(value instanceof Integer){
                return criteriaBuilder.equal(root.get(field), value);
            }
        }

        if(operator.equalsIgnoreCase("LIKE") && field.equalsIgnoreCase("title")){
            return criteriaBuilder.like(root.get("title"), "%" + value.toString() + "%");
        }

        if (operator.equalsIgnoreCase("=")) {
            if (value instanceof Date) {
                return criteriaBuilder.equal(root.get(field), value);
            }
        }
        return null;
    }
}
