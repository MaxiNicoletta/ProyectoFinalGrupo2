package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.TouristicPackageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristicPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public TouristicPackageDTO entityToDTO(){
        return new TouristicPackageDTO();
    }

    public TouristicPackage dtoToEntity(TouristicPackageDTO touristicPackageDTO){
        return new TouristicPackage();
    }


}
