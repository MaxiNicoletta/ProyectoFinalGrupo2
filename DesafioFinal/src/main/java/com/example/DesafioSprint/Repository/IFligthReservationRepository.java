package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.DTOs.ReservasVueloRequestDTO;
import com.example.DesafioSprint.Entities.ReservaVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IFligthReservationRepository extends JpaRepository<ReservaVuelo, Long> {
}

