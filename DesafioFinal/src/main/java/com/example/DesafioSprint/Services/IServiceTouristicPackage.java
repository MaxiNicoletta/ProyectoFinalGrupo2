package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.PackageResponseDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageDTO;


import java.util.List;

public interface IServiceTouristicPackage {
    public PackageResponseDTO addTouristicPackage(TouristicPackageDTO packageDTO);
    public PackageResponseDTO updateTouristicPackage(TouristicPackageDTO touristicPackageDTO,int id);
    public List<TouristicPackageDTO> getPackages();
    public PackageResponseDTO deletePackage(int id);
}
