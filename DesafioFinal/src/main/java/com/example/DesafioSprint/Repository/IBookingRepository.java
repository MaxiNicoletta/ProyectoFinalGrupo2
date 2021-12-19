package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.Booking;
import com.example.DesafioSprint.Entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IBookingRepository extends JpaRepository<Booking,Long> {

    @Query("SELECT b FROM booking b")
    public List<Booking> getBookings();

    @Query("SELECT p FROM persona p" )
    public List<Persona> getPeople();

}
