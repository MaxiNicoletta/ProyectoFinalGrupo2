package com.example.DesafioSprint.Repository;


import com.example.DesafioSprint.Edentity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFlightRepository extends JpaRepository<Vuelo, Long> {

    
    public void getFlightsFiltered();


//    public void addReservaHotel(ReservaHotel rsv);
    //Esto iria con el .save()
//    public void addReservaVuelo(ReservaVuelo rsv);
}
