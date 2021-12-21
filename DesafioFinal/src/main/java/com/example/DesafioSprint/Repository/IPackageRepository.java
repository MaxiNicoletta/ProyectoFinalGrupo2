package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.TouristicPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPackageRepository extends JpaRepository<TouristicPackage,Long> {

}
