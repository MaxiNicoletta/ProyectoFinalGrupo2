package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Persona,String> {
}
