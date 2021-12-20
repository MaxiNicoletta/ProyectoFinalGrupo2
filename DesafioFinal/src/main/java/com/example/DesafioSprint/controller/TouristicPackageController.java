package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageResponseDTO;
import com.example.DesafioSprint.DTOs.TouristicPackageRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/touristicpackage")
public class TouristicPackageController {

    private TouristicPackageServiceImpl service;

    @PostMapping("/new/")
    public ResponseEntity<TouristicPackageResponseDTO> addNewTouristicPackage(@RequestBody TouristicPackageRequestDTO touristicPackageRequest){
        return new ResponseEntity<>(service.addTouristicPackage(touristicPackageRequest), HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<TouristicPackageResponseDTO> updateTouristicPackage(@RequestParam Long id, @RequestBody TouristicPackageRequestDTO request) {
        return new ResponseEntity<>(service.updateTouristPackage, HttpStatus.OK);
    }

    @GetMapping("s")
    public ArrayList<TouristicPackageDTO> getAllTouristicPackages(){
        return service.findAllTouristicPackages();
    }


    @DeleteMapping("/delete")
    public ResponseEntity<TouristicPackageResponseDTO> deleteTouristicPackage(@RequestParam Long id){
        return new ResponseEntity<>(service.deleteTouristicPackage(), HttpStatus.OK);
    }

}
