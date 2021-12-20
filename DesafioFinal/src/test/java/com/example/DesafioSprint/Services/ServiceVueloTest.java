package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadVuelosDTO;
import com.example.DesafioSprint.DTOs.FlightResponseDTO;
import com.example.DesafioSprint.DTOs.VueloDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;
import com.example.DesafioSprint.Entities.Vuelo;
import com.example.DesafioSprint.Repository.FlightRepository;
import com.example.DesafioSprint.Repository.IFlightRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceVueloTest {

    @Mock
    IFlightRepository repository;

    @InjectMocks
    ServiceVuelo srvVuelo;

    private List<Vuelo> list;

    @BeforeEach
    void cargarServ() {
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "23/02/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {}
        Vuelo vuelo1 = new Vuelo("TUPI-3369","vuelo1","Tucumán","Puerto Iguazú",date1,date2,"Business",12530);
        list = new ArrayList<>();
        list.add(vuelo1);
    }
    @Test
    void addFlight() throws FechasException, VuelosException {
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "23/02/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {}
        VueloDTO vuelo1 = new VueloDTO("TUPI-3369","vuelo1","Tucumán","Puerto Iguazú",date1,date2,"Business",12530);
        FlightResponseDTO res = srvVuelo.addFlight(vuelo1);
        assertTrue(res.getMessage()== "Vuelo dado de alta correctamente");
    }

    @Test
    void addFlightExist(){
        when(repository.existsVuelo("TUPI-3369")).thenReturn(true);
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "23/02/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {}
        VueloDTO vuelo1 = new VueloDTO("TUPI-3369","vuelo1","Tucumán","Puerto Iguazú","Business",12530,date1,date2);
        VuelosException ex =  assertThrows(VuelosException.class, () -> srvVuelo.addFlight(vuelo1));
        assertEquals("Ya existe un vuelo con ese numero",ex.getERROR());
    }
    @Test
    void addFlighDate(){
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "23/02/2022";
        Date date1 = null, date2 = null;
        try {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        } catch (Exception e) {}
        VueloDTO vuelo1 = new VueloDTO("TUPI-3369","vuelo1","Tucumán","Puerto Iguazú","Business",12530,date2,date1);
        FechasException ex =  assertThrows(FechasException.class, () -> srvVuelo.addFlight(vuelo1));
        assertEquals("La fecha de vuelta debe ser mayor a la de ida",ex.getERROR());
    }


    @Test
    void getVuelosTest() throws VuelosException{
        when(repository.findAll()).thenReturn(list);
        List<VueloDTO> res = srvVuelo.getFlights();
        assertFalse(res.isEmpty());
    }

    @Test
    void getVuelosTestVacio(){
        List<Vuelo> aux = new ArrayList<>();
        when(repository.findAll()).thenReturn(aux);
        VuelosException ex = assertThrows(VuelosException.class, () -> srvVuelo.getFlights());
        assertEquals("No hay vuelos en el repositorio",ex.getERROR());
    }

    @Test
    void disponibilidadVuelosTest() throws UbicacionException, FechasException, VuelosException {
        //Arrange
        when(repository.findAll()).thenReturn(list);
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "23/02/2022";
        Date date1= null,date2 = null;
        try {
            date1= new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        }
        catch (Exception e){}
        DisponibilidadVuelosDTO rsv = new DisponibilidadVuelosDTO(date1,date2,"Tucumán","Puerto Iguazú") ;
        //Act
        List<VueloDTO> res = srvVuelo.disponibilidadVuelos(rsv);
        //Assert
        assertEquals("TUPI-3369",res.get(0).getFlightNumber());
    }

    @Test
    void disponibilidadVuelosTestDestinoNo() throws UbicacionException, FechasException, VuelosException {
        //Arrange
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "23/02/2022";
        Date date1= null,date2 = null;
        try {
            date1= new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        }
        catch (Exception e){}
        DisponibilidadVuelosDTO rsv = new DisponibilidadVuelosDTO(date1,date2,"Tucumán","Puerto Iguazúhdfghdfgh") ;
        //Act
        UbicacionException ex = assertThrows(UbicacionException.class, () -> srvVuelo.disponibilidadVuelos(rsv));
        assertEquals("El destino elegido no existe",ex.getERROR());
    }

    @Test
    void disponibilidadVuelosTestOrigenNo() throws UbicacionException, FechasException, VuelosException {
        //Arrange
        when(repository.findAll()).thenReturn(list);
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "23/02/2022";
        Date date1= null,date2 = null;
        try {
            date1= new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        }
        catch (Exception e){}
        DisponibilidadVuelosDTO rsv = new DisponibilidadVuelosDTO(date1,date2,"Tucumánjkghk","Puerto Iguazú") ;
        //Act
        UbicacionException ex = assertThrows(UbicacionException.class, () -> srvVuelo.disponibilidadVuelos(rsv));
        assertEquals("El origen elegido no existe",ex.getERROR());
    }

    @Test
    void disponibilidadVuelosTestFechasNo(){
        //Arrange
        when(repository.findAll()).thenReturn(list);
        String fechaOrigin = "15/02/2022";
        String fechaVuelta = "10/02/2022";
        Date date1= null,date2 = null;
        try {
            date1= new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        }
        catch (Exception e){}
        DisponibilidadVuelosDTO rsv = new DisponibilidadVuelosDTO(date1,date2,"Tucumán","Puerto Iguazú") ;
        //Act
        FechasException ex = assertThrows(FechasException.class, () -> srvVuelo.disponibilidadVuelos(rsv));
        assertEquals("La fecha de vuelta debe ser mayor a la de ida",ex.getERROR());
    }
    @Test
    void disponibilidadVuelosTestExistencia() throws UbicacionException, FechasException, VuelosException {
        //Arrange
        when(repository.findAll()).thenReturn(list);
        String fechaOrigin = "10/02/2022";
        String fechaVuelta = "22/02/2022";
        Date date1= null,date2 = null;
        try {
            date1= new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
        }
        catch (Exception e){}
        DisponibilidadVuelosDTO rsv = new DisponibilidadVuelosDTO(date1,date2,"Tucumán","Puerto Iguazú") ;
        //Assert
        assertThrows(VuelosException.class, () -> srvVuelo.disponibilidadVuelos(rsv));
    }

}