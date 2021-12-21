package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TouristicPackage {
    @Id
    private int packageNumber;
    private String name;
    private Date creation_date;
    private int clientId;

    @OneToOne
    @JoinColumn(name = "bookins_id")
    private BookingsOrReservations bookingsOrReservations;

    public TouristicPackage(int packageNumber, String name, Date creation_date, int clientId) {
        this.packageNumber = packageNumber;
        this.name = name;
        this.creation_date = creation_date;
        this.clientId = clientId;
    }

    public TouristicPackageDTO entityToDTO() {
        return new TouristicPackageDTO(getPackageNumber(), getName(), getCreation_date(), getClientId(), getBookingsOrReservations().entityToDTO());
    }

    public TouristicPackage dtoToEntity(TouristicPackageDTO touristicPackageDTO) {
        return new TouristicPackage(touristicPackageDTO.getPackageNumber(), touristicPackageDTO.getName(), touristicPackageDTO.getCreation_date(), touristicPackageDTO.getClientId(), bookingsOrReservations.DTOtoEntity(touristicPackageDTO.getBookingsOrReservations()));
    }

}
