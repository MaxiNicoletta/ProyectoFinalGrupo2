package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.TouristicPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPackageRepository extends JpaRepository<TouristicPackage,Integer> {

}
