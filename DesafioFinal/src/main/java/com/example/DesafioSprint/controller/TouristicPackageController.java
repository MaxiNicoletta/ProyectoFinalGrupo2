package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Services.IServiceTouristicPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/touristicpackage")
public class TouristicPackageController {

    @Autowired
     IServiceTouristicPackage service;

    @PostMapping("/new")
    public ResponseEntity<PackageResponseDTO> addNewTouristicPackage(@RequestBody TouristicPackageDTO touristicPackageDTO){
        return new ResponseEntity<>(service.addTouristicPackage(touristicPackageDTO), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<PackageResponseDTO> updateTouristicPackage(@RequestParam int packageNumber, @RequestBody TouristicPackageDTO touristicPackageDTO) {
        return new ResponseEntity<>(service.updateTouristicPackage(touristicPackageDTO,packageNumber), HttpStatus.OK);
    }

    @GetMapping("")
    public List<TouristicPackageDTO> getAllTouristicPackages(){
        return service.getPackages();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PackageResponseDTO> deleteTouristicPackage(@RequestParam int packageNumber){
        return new ResponseEntity<>(service.deletePackage(packageNumber), HttpStatus.OK);
    }

}
