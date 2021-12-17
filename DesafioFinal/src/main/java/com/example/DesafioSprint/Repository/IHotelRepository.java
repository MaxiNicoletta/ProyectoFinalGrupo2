package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Long> {

    @Query("select h from Hotel h where h.hotelCode =:number ")
    Hotel findHoteltByCod(@Param("number") String number);

    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM Hotel h WHERE h.place = :destination")
    Boolean existsDestinationHotel(@Param("destination") String destination);

    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM Hotel h WHERE h.hotelCode = :hotelCode")
    Boolean existsHotel(@Param("flightNumber") String hotelCode);
}
