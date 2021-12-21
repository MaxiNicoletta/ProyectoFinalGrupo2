package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.DTOs.ReservasVueloRequestDTO;
import com.example.DesafioSprint.Entities.ReservaVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFligthReservationRepository extends JpaRepository<ReservaVuelo, Long> {
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Reserva v WHERE v.id = :id")
    Boolean existsReservaVueloById(@Param("id") Long id);
/*
    @Query("select r.paymentMethod.id FROM Reserva r where r.id =: id")
    Long PagoID(@Param("id") Long id);
 */
}

