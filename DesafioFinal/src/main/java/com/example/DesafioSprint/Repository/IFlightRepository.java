package com.example.DesafioSprint.Repository;


import com.example.DesafioSprint.Edentity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFlightRepository extends JpaRepository<Vuelo, Long> {

    @Query("select v from Vuelo v where v.flightNumber =:number ")
    Vuelo findFlightByCod(@Param("number") String number);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Vuelo v WHERE v.destination = :destination")
    Boolean existsDestinationVuelo(@Param("destination") String destination);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Vuelo v WHERE v.origin = :origin")
    Boolean existsOriginVuelo(@Param("origin") String origin);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END FROM Vuelo v WHERE v.flightNumber = :flightNumber")
    Boolean existsVuelo(@Param("flightNumber") String flightNumber);

}
