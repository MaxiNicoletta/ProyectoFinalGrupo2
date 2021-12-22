package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.PackageResponseDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import com.example.DesafioSprint.Entities.*;
import com.example.DesafioSprint.Repository.IBookingRepository;
import com.example.DesafioSprint.Repository.IFligthReservationRepository;
import com.example.DesafioSprint.Repository.IPackageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ServiceTouristicPackage implements IServiceTouristicPackage {

    private final IPackageRepository packageRepository;

    public ServiceTouristicPackage(IPackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public PackageResponseDTO addTouristicPackage(TouristicPackageDTO packageDTO) {
        TouristicPackage touristicPackage = new TouristicPackage();
            touristicPackage= touristicPackage.dtoToEntity(packageDTO);
            packageRepository.save(touristicPackage);
        return new PackageResponseDTO("Paquete Turistico dado de alta correctamente");
    }

    @Override
    public PackageResponseDTO updateTouristicPackage(TouristicPackageDTO touristicPackageDTO, int id) {
        TouristicPackage touristicPackage = packageRepository.getById(id);
        if (touristicPackage != null) {
            packageRepository.save(touristicPackage);
        }
        return new PackageResponseDTO("Paquete turistico modificado correctamente ");
    }

    @Override
    public List<TouristicPackageDTO> getPackages() {
        List<TouristicPackage> touristicPackages = packageRepository.findAll();
        List<TouristicPackageDTO> result = new ArrayList<>();
        for (TouristicPackage touristicPackage : touristicPackages) {
            TouristicPackageDTO touristicPackageDTO = new TouristicPackageDTO();
            touristicPackage.dtoToEntity(touristicPackageDTO);
            result.add(touristicPackageDTO);
        }
        return result;
    }

    @Override
    public PackageResponseDTO deletePackage(int id) {
        TouristicPackage touristicPackage = packageRepository.getById(id);
        packageRepository.delete(touristicPackage);
        return new PackageResponseDTO("Paquete turistico borrado correctamente");
    }

}
