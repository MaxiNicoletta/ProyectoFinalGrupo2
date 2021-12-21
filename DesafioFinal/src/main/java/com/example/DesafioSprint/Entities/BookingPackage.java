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
public class BookingPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "bookingPackage")
    private Booking firstBooking;
    @OneToOne(mappedBy = "bookingPackage")
    private Booking secondBooking;
    @ManyToMany(mappedBy = "bookingPackages", cascade = CascadeType.ALL)
    private List<TouristicPackage> touristicPackagesH;
}
