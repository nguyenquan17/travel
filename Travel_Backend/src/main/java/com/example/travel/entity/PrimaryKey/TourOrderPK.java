package com.example.travel.entity.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TourOrderPK implements Serializable {
    @Column(name = "id_user", nullable = false)
    private int idUser;

    @Column(name = "id_tourDetail", nullable = false)
    private int idTourDetail;


}
