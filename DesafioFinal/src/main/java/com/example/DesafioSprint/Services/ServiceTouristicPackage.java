package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.PackageResponseDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import com.example.DesafioSprint.Entities.*;
import com.example.DesafioSprint.Repository.IBookingRepository;
import com.example.DesafioSprint.Repository.IFligthReservationRepository;
import com.example.DesafioSprint.Repository.IPackageRepository;

import java.util.List;

public class ServiceTouristicPackage implements IServiceTouristicPackage {

    private final IPackageRepository packageRepository;
    private final IBookingRepository bookingRepository;
    private final IFligthReservationRepository fligthReservationRepository;

    public ServiceTouristicPackage(IPackageRepository packageRepository, IBookingRepository bookingRepository, IFligthReservationRepository fligthReservationRepository) {
        this.packageRepository = packageRepository;
        this.bookingRepository = bookingRepository;
        this.fligthReservationRepository = fligthReservationRepository;
    }

    @Override
    public PackageResponseDTO addTouristicPackage(TouristicPackageDTO packageDTO) {
return null;
    }

    @Override
    public PackageResponseDTO updateTouristicPackage() {
        return null;
    }

    @Override
    public List<TouristicPackage> getPackages() {
        return null;
    }

    @Override
    public PackageResponseDTO deletePackage() {
        return null;
    }

    private TouristicPackage getPackage(Long firstId,Long secondId) {
        TouristicPackageDTO touristicPackageDTO;
        TouristicPackage touristicPackage = new TouristicPackage();

        FlightReservationPackage flightReservationPackage;
        BookingPackage bookingPackage;
        BookingFlightPackage bookingFlightPackage;
        Booking booking = bookingRepository.getById(firstId);
        Booking secondBooking = bookingRepository.getById(firstId);
        ReservaVuelo reservation = fligthReservationRepository.getById(firstId);
        ReservaVuelo secondReservation = fligthReservationRepository.getById(secondId);
        if (booking == null && secondBooking == null) {
            flightReservationPackage = new FlightReservationPackage(reservation, secondReservation);
            touristicPackage.setFlightReservationPackage(flightReservationPackage);
        }
        if (reservation == null && secondReservation == null) {
            bookingPackage = new BookingPackage(booking, secondBooking);
            touristicPackage.setBookingPackage(bookingPackage);
        }
        if (booking == null && reservation != null) {
            bookingFlightPackage = new BookingFlightPackage(secondBooking, reservation);
            touristicPackage.setBookingFlightPackage(bookingFlightPackage);
        }
        if (secondBooking == null && reservation != null) {
            bookingFlightPackage = new BookingFlightPackage(booking, reservation);
            touristicPackage.setBookingFlightPackage(bookingFlightPackage);
        }
        if (booking != null && reservation == null) {
            bookingFlightPackage = new BookingFlightPackage(booking, secondReservation);
            touristicPackage.setBookingFlightPackage(bookingFlightPackage);
        }
        if (secondBooking != null && reservation == null) {
            bookingFlightPackage = new BookingFlightPackage(secondBooking, secondReservation);
            touristicPackage.setBookingFlightPackage(bookingFlightPackage);
        }
        return touristicPackage;
    }

}
