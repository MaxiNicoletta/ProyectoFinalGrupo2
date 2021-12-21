
package com.example.DesafioSprint.Services;
import com.example.DesafioSprint.DTOs.DisponibilidadHotelDTO;
import com.example.DesafioSprint.DTOs.HotelDTO;
import com.example.DesafioSprint.DTOs.HotelResponseDTO;
import com.example.DesafioSprint.DTOs.ListHotelesDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Entities.Hotel;
import com.example.DesafioSprint.Exceptions.VuelosException;
import com.example.DesafioSprint.Repository.IHotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceHotelTest {

    @Mock
    IHotelRepository repository;

    @InjectMocks
    ServiceHotel srvHotel;

    private List<Hotel> list;

    @BeforeEach
    void loadServ(){
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "19/03/2022";
        String fechaOrigin2 = "12/02/2022";
        String fechaVuelta2 = "17/04/2022";
        Date date1 = null, date2 = null;
        Date date3 = null, date4 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
            date3 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin2);
            date4 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta2);
        } catch (Exception e) {}
        Hotel hotel1 = new Hotel("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, date1, date2, false);
        Hotel hotel2 = new Hotel("BH-0002", "Hotel Bristol 2", "Buenos Aires", "Doble", 7200, date3, date4, false);
        list = new ArrayList<>();
        list.add(hotel1);
        list.add(hotel2);
    }

    @Test
    void addHotel() throws FechasException, VuelosException {
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "19/03/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {}
        HotelDTO hotel1 = new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, date1, date2, false);
        HotelResponseDTO res = srvHotel.addHotel(hotel1);
        assertTrue(res.getMessage()== "Hotel dado de alta correctamente");
    }

    @Test
    void addHotelDate() throws FechasException, VuelosException {
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "19/03/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {}
        HotelDTO hotel1 = new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, date2, date1, false);
        FechasException ex =  assertThrows(FechasException.class, () -> srvHotel.addHotel(hotel1));
        assertEquals("La fecha de salida debe ser mayor a la de ida",ex.getERROR());
    }

    @Test
    void getHoteles() throws HotelesException {
        when(repository.findAll()).thenReturn(list);
        ListHotelesDTO res = srvHotel.getHoteles();
        assertTrue(res.getHoteles().get(0) != null);
    }


    @Test
    void getHotelesEmpty(){
        List<Hotel> aux = new ArrayList<>();
        when(repository.findAll()).thenReturn(aux);
        HotelesException ex = assertThrows(HotelesException.class, () -> srvHotel.getHoteles());
        assertEquals("No hay hoteles en el repositorio",ex.getERROR());
    }
}

