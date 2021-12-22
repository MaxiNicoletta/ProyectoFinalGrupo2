package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.BookingsOrReservationsDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TouristicPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int packageNumber;
    private String name;
    private Date creation_date;
    private int client_Id;
    private int bookResId1;
    private int bookResId2;

    public TouristicPackageDTO entityToDTO() {
        return new TouristicPackageDTO(getPackageNumber(), getName(), getCreation_date().toString(), getClient_Id(), getBookResId1(),getBookResId2());
    }

    public Date getDate(TouristicPackageDTO touristicPackageDTO) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(touristicPackageDTO.getCreation_date());
        } catch (Exception e) {
        }
        return date;
    }

    public TouristicPackage dtoToEntity(TouristicPackageDTO touristicPackageDTO) {
        TouristicPackage touristicPackage = new TouristicPackage(touristicPackageDTO.getPackageNumber(), touristicPackageDTO.getName(), getDate(touristicPackageDTO), touristicPackageDTO.getClient_Id(),touristicPackageDTO.getBookResId1(),touristicPackageDTO.getBookResId2());
        return touristicPackage;
    }

}
