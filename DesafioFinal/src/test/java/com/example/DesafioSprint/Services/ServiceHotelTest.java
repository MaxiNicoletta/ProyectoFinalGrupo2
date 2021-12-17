package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadHotelDTO;
import com.example.DesafioSprint.DTOs.ListHotelesDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Identity.Hotel;
import com.example.DesafioSprint.Repository.IRepositoryData;
import com.example.DesafioSprint.Repository.RepositoryData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.standard.MediaSize;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceHotelTest {

    @Mock
    RepositoryData repository;

    @InjectMocks
    ServiceHotel srvHotel;

    private List<Hotel> list;

    @BeforeEach
    void cargarServ(){
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
    void getHotelesTest() throws HotelesException {
        when(repository.getHoteles()).thenReturn(list);
        ListHotelesDTO res = srvHotel.getHoteles();
        assertTrue(res.getHoteles().get(0) != null);
    }

    @Test
    void getHotelesTestVacio(){
        List<Hotel> aux = new ArrayList<>();
        when(repository.getHoteles()).thenReturn(aux);
        HotelesException ex = assertThrows(HotelesException.class, () -> srvHotel.getHoteles());
        assertEquals("No hay hoteles en el repositorio",ex.getERROR());
    }

    @Test
    void existsHotelesTestOk() {
        when(repository.getHoteles()).thenReturn(list);
        assertTrue(srvHotel.existsHoteles("HB-0001"));
    }

    @Test
    void existsHotelesTestFalla() {
        assertFalse(srvHotel.existsHoteles("dsfgdsfg"));
    }

    @Test
    void existsDestinationTestOk() {
        when(repository.getHoteles()).thenReturn(list);
        assertTrue(srvHotel.existsDestination("Buenos Aires"));
    }

    @Test
    void existsDestinationTestFalla() {
        assertFalse(srvHotel.existsDestination("dgfhfdghdfgh"));
    }

    @Test
    void getHotelesDisponiblesTestOk() throws FechasException, HotelesException {
        //Arrange
        when(repository.getHoteles()).thenReturn(list);
        String fechaOrigin = "13/02/2022";
        String fechaVuelta = "17/02/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {
        }
        DisponibilidadHotelDTO rsv = new DisponibilidadHotelDTO(date1, date2, "Buenos Aires");
        //Act
        ListHotelesDTO res = srvHotel.getHotelesDisponibles(rsv);
        //Assert
        assertEquals("BH-0002", res.getHoteles().get(1).getHotelCode());
    }

    @Test
    void getHotelesDisponiblesTestDestinationNo(){
        //Arrange
        when(repository.getHoteles()).thenReturn(list);
        String fechaOrigin = "12/02/2022";
        String fechaVuelta = "17/02/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {
        }
        DisponibilidadHotelDTO rsv = new DisponibilidadHotelDTO(date1, date2, "hhjghj");
        //Assert
        HotelesException ex =  assertThrows(HotelesException.class, () -> srvHotel.getHotelesDisponibles(rsv));
        assertEquals("El destino elegido no existe",ex.getERROR());
    }



    @Test
    void getHotelesDisponiblesTestFechaNo() throws FechasException, HotelesException {
        //Arrange
        when(repository.getHoteles()).thenReturn(list);
        String fechaOrigin = "17/02/2022";
        String fechaVuelta = "12/02/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {
        }
        DisponibilidadHotelDTO rsv = new DisponibilidadHotelDTO(date1, date2, "Buenos Aires");
        //Assert
        FechasException ex = assertThrows(FechasException.class, () -> srvHotel.getHotelesDisponibles(rsv));
        assertEquals("La fecha de salida debe ser mayor a la de entrada",ex.getERROR());
    }

    @Test
    void getHotelesDisponiblesTestExistencia() throws FechasException, HotelesException {
        //Arrange
        when(repository.getHoteles()).thenReturn(list);
        String fechaOrigin = "12/02/2024";
        String fechaVuelta = "13/02/2024";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {
        }
        DisponibilidadHotelDTO rsv = new DisponibilidadHotelDTO(date1, date2, "Buenos Aires");
        //Assert
        assertThrows(HotelesException.class, () -> srvHotel.getHotelesDisponibles(rsv));
    }


}