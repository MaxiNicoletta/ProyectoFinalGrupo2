package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookingRepository extends JpaRepository<ReservaHotel,Long> {
    public List<ReservaHotel> getBookings();

}
