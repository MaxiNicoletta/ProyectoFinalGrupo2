package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Long> {

//    public void addReservaHotel(ReservaHotel rsv);

}
