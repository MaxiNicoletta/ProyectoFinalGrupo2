package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TouristicPackage {
    @Id
    private int packageNumber;
    private String name;
    private Date creation_date;
    private int clientId;
    @ManyToMany(mappedBy = "touristicPackagesH", fetch = FetchType.LAZY)
    @JoinTable(name = "booking_packages",
            joinColumns = @JoinColumn(name = "package_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"))
    private List<BookingPackage> bookingPackages;
    @ManyToMany(mappedBy = "touristicPackagesFH", fetch = FetchType.LAZY)
    @JoinTable(name = "booking_reservation_ packages",
            joinColumns = @JoinColumn(name = "package_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "booking_reservation_package_id", referencedColumnName = "id"))
    private List<BookingFlightPackage> bookingFlightPackages;
    @ManyToMany(mappedBy = "touristicPackagesF", fetch = FetchType.LAZY)
    @JoinTable(name = "reservation_ packages", joinColumns = @JoinColumn(name = "package_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reservation_id", referencedColumnName = "id"))
    private List<FlightReservationPackage> flightReservationPackages;



//        public TouristicPackageDTO entityToDTO(){
//        return new TouristicPackageDTO(getBooking().bookingToDTO(),getReservaVuelo().entityToDTO());
//    }
//
//    public TouristicPackage dtoToEntity(TouristicPackageDTO touristicPackageDTO){
//        return new TouristicPackage();
//    }

}
