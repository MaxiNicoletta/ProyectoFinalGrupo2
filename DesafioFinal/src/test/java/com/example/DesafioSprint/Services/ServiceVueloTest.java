package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.DisponibilidadVuelosDTO;
import com.example.DesafioSprint.DTOs.VueloDTO;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Exceptions.VuelosException;
import com.example.DesafioSprint.Edentity.Vuelo;
import com.example.DesafioSprint.Repository.FlightRepository;
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
    FlightRepository repository;

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
        Vuelo vuelo1 = new Vuelo("TUPI-3369","Tucumán","Puerto Iguazú",date1,date2,"Business",12530);
        list = new ArrayList<>();
        list.add(vuelo1);
    }

    @Test
    void getVuelosTest() throws VuelosException{
        when(repository.getVuelos()).thenReturn(list);
        List<VueloDTO> res = srvVuelo.getFlights();
        assertFalse(res.isEmpty());
    }

    @Test
    void getVuelosTestVacio(){
        List<Vuelo> aux = new ArrayList<>();
        when(repository.getVuelos()).thenReturn(aux);
        VuelosException ex = assertThrows(VuelosException.class, () -> srvVuelo.getFlights());
        assertEquals("No hay vuelos en el repositorio",ex.getERROR());
    }

    @Test
    void existsVueloTestOk() {
        when(repository.getVuelos()).thenReturn(list);
        assertTrue(srvVuelo.existsVuelo("TUPI-3369"));
    }

    @Test
    void existsVueloTestFalla() {
        assertFalse(srvVuelo.existsVuelo("BAPI-1235gdfgdfg"));
    }

    @Test
    void existsDestinationVueloTestOk() {
        when(repository.getVuelos()).thenReturn(list);
        assertTrue(srvVuelo.existsDestinationVuelo("Puerto Iguazú"));
    }

    @Test
    void existsDestinationVueloTestFalla() {
        assertFalse(srvVuelo.existsDestinationVuelo("Buenos Airesfdgdsfg"));
    }

    @Test
    void existsOriginVueloTestOk() {
        when(repository.getVuelos()).thenReturn(list);
        assertTrue(srvVuelo.existsOriginVuelo("Tucumán"));
    }

    @Test
    void existsOriginVueloTestFalla() {
        assertFalse(srvVuelo.existsOriginVuelo("Buenos Airesgfhdfgh"));
    }

    @Test
    void disponibilidadVuelosTest() throws UbicacionException, FechasException, VuelosException {
        //Arrange
        when(repository.getVuelos()).thenReturn(list);
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
        when(repository.getVuelos()).thenReturn(list);
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
        when(repository.getVuelos()).thenReturn(list);
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
        when(repository.getVuelos()).thenReturn(list);
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