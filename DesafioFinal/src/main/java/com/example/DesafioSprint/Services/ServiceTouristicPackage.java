package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.PackageResponseDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import com.example.DesafioSprint.Entities.Booking;
import com.example.DesafioSprint.Entities.TouristicPackage;
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

    private TouristicPackageDTO getPackage(Long firstId,Long secondId){
        TouristicPackageDTO touristicPackageDTO = new TouristicPackageDTO();
        Booking booking = bookingRepository.getById(firstId);
        Booking bookingAux = 
    }



}
