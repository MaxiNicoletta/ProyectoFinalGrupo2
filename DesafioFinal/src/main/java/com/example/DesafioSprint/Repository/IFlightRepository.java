package com.example.DesafioSprint.Repository;


import com.example.DesafioSprint.Entities.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlightRepository extends JpaRepository<Vuelo, Long> {

    
    public void getFlightsFiltered();


//    public void addReservaHotel(ReservaHotel rsv);
    //Esto iria con el .save()
//    public void addReservaVuelo(ReservaVuelo rsv);
}
