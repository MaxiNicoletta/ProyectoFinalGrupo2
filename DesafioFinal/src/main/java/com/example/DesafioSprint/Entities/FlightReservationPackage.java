package com.example.DesafioSprint.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FlightReservationPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "flightReservationPackage")
    private ReservaVuelo firstFlightReservation;
    @OneToOne(mappedBy = "flightReservationPackage")
    private ReservaVuelo secondFlightReservation;
    @ManyToMany(mappedBy = "flightReservationPackages", cascade = CascadeType.ALL)
    private List<TouristicPackage> touristicPackagesF;

}
