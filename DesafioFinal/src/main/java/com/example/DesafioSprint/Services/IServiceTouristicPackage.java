package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.FlightReservationPackageDTO;
import com.example.DesafioSprint.DTOs.PackageResponseDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import com.example.DesafioSprint.Entities.TouristicPackage;

import java.util.List;

public interface IServiceTouristicPackage {
    public PackageResponseDTO addTouristicPackage(TouristicPackageDTO packageDTO);
    public PackageResponseDTO updateTouristicPackage();
    public List<TouristicPackage> getPackages();
    public PackageResponseDTO deletePackage();
}
