package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.PackageResponseDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageResponseDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageRequestDTO;
import com.example.DesafioSprint.Services.IServiceTouristicPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/touristicpackage")
public class TouristicPackageController {

    @Autowired
     IServiceTouristicPackage service;

    @PostMapping("/new/")
    public ResponseEntity<PackageResponseDTO> addNewTouristicPackage(@RequestBody TouristicPackageDTO touristicPackageDTO){
        return new ResponseEntity<>(service.addTouristicPackage(touristicPackageDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<PackageResponseDTO> updateTouristicPackage(@RequestParam int id, @RequestBody TouristicPackageDTO touristicPackageDTO) {
        return new ResponseEntity<>(service.updateTouristicPackage(touristicPackageDTO,id), HttpStatus.OK);
    }

    @GetMapping("s")
    public List<TouristicPackageDTO> getAllTouristicPackages(){
        return service.getPackages();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PackageResponseDTO> deleteTouristicPackage(@RequestParam int id){
        return new ResponseEntity<>(service.deletePackage(id), HttpStatus.OK);
    }

}
