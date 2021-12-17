package com.example.DesafioSprint.Repository;


import com.example.DesafioSprint.Identity.Hotel;
import com.example.DesafioSprint.Identity.ReservaHotel;
import com.example.DesafioSprint.Identity.ReservaVuelo;
import com.example.DesafioSprint.Identity.Vuelo;

import java.util.List;

public interface IRepositoryData {
    public void addReservaHotel(ReservaHotel rsv);

    public List<Hotel> loadHoteles();

    public List<Vuelo> loadVuelos();

    public void addReservaVuelo(ReservaVuelo rsv);
}
