//
//package com.example.DesafioSprint.Services;
//import com.example.DesafioSprint.DTOs.DisponibilidadHotelDTO;
//import com.example.DesafioSprint.DTOs.HotelDTO;
//import com.example.DesafioSprint.DTOs.HotelResponseDTO;
//import com.example.DesafioSprint.DTOs.ListHotelesDTO;
//import com.example.DesafioSprint.Exceptions.FechasException;
//import com.example.DesafioSprint.Exceptions.HotelesException;
//import com.example.DesafioSprint.Entities.Hotel;
//import com.example.DesafioSprint.Exceptions.VuelosException;
//import com.example.DesafioSprint.Repository.IHotelRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ServiceHotelTest {
//
//    @Mock
//    IHotelRepository repository;
//
//    @InjectMocks
//    ServiceHotel srvHotel;
//
//    private List<Hotel> list;
//
//    @BeforeEach
//    void loadServ(){
//        String fechaOrigin = "10/02/2022";
//        String fechaVuelta = "19/03/2022";
//        String fechaOrigin2 = "12/02/2022";
//        String fechaVuelta2 = "17/04/2022";
//        Date date1 = null, date2 = null;
//        Date date3 = null, date4 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//            date3 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin2);
//            date4 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta2);
//        } catch (Exception e) {}
//        Hotel hotel1 = new Hotel("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, date1, date2, false);
//        Hotel hotel2 = new Hotel("BH-0002", "Hotel Bristol 2", "Buenos Aires", "Doble", 7200, date3, date4, false);
//        list = new ArrayList<>();
//        list.add(hotel1);
//        list.add(hotel2);
//    }
//
//    @Test
//    void addHotel() throws FechasException, VuelosException {
//        String fechaOrigin = "10/02/2022";
//        String fechaVuelta = "19/03/2022";
//        Date date1 = null, date2 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//        } catch (Exception e) {}
//        HotelDTO hotel1 = new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, date1, date2, false);
//        HotelResponseDTO res = srvHotel.addHotel(hotel1);
//        assertTrue(res.getMessage()== "Hotel dado de alta correctamente");
//    }
//
//    @Test
//    void addHotelExist() throws FechasException, VuelosException {
//        when(repository.existsHotel("HB-0001")).thenReturn(true);
//        String fechaOrigin = "10/02/2022";
//        String fechaVuelta = "19/03/2022";
//        Date date1 = null, date2 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//        } catch (Exception e) {}
//        HotelDTO hotel1 = new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, date1, date2, false);
//        HotelesException ex =  assertThrows(HotelesException.class, () -> srvHotel.addHotel(hotel1));
//        assertEquals("Ya existe un hotel con ese numero",ex.getERROR());
//    }
//    @Test
//    void addHotelDate() throws FechasException, VuelosException {
//        String fechaOrigin = "10/02/2022";
//        String fechaVuelta = "19/03/2022";
//        Date date1 = null, date2 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//        } catch (Exception e) {}
//        HotelDTO hotel1 = new HotelDTO("HB-0001", "Hotel Bristol", "Buenos Aires", "Single", 5435, date2, date1, false);
//        FechasException ex =  assertThrows(FechasException.class, () -> srvHotel.addHotel(hotel1));
//        assertEquals("La fecha de salida debe ser mayor a la de ida",ex.getERROR());
//    }
//
//    @Test
//    void getHoteles() throws HotelesException {
//        when(repository.findAll()).thenReturn(list);
//        ListHotelesDTO res = srvHotel.getHoteles();
//        assertTrue(res.getHoteles().get(0) != null);
//    }
//
//    @Test
//    void updateHotel() throws HotelesException {
//        when(repository.findAll()).thenReturn(list);
//        String fechaOrigin = "10/02/2022";
//        String fechaVuelta = "19/03/2022";
//        Date date1 = null, date2 = null;
//        Date date3 = null, date4 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//
//        } catch (Exception e) {}
//        HotelDTO hotel1 = new HotelDTO("HB-0001", "Hotel BristolModificado", "Buenos Aires", "Single", 5435, date1, date2, false);
//        HotelResponseDTO res = srvHotel.updateHotel("HB-0001",hotel1);
//        assertTrue(res.getMessage()== "Hotel modificado correctamente");
//    }
//
//    @Test
//    void updateHotelNoExist(){
//        //Arrange
//        when(repository.findAll()).thenReturn(list);
//        HotelesException ex =  assertThrows(HotelesException.class, () -> srvHotel.updateHotel("dfgdsfgsdfg",null));
//        //Assert
//        assertEquals("No existe hotel con ese codigo",ex.getERROR());
//    }
//
//    @Test
//    void getHotelesEmpty(){
//        List<Hotel> aux = new ArrayList<>();
//        when(repository.findAll()).thenReturn(aux);
//        HotelesException ex = assertThrows(HotelesException.class, () -> srvHotel.getHoteles());
//        assertEquals("No hay hoteles en el repositorio",ex.getERROR());
//    }
//
//    @Test
//    void getHotelAvailableOk() throws FechasException, HotelesException {
//        //Arrange
//        when(repository.findAll()).thenReturn(list);
//        String fechaOrigin = "13/02/2022";
//        String fechaVuelta = "17/02/2022";
//        Date date1 = null, date2 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//        } catch (Exception e) {
//        }
//        DisponibilidadHotelDTO rsv = new DisponibilidadHotelDTO(date1, date2, "Buenos Aires");
//        //Act
//        ListHotelesDTO res = srvHotel.getHotelesDisponibles(rsv);
//        //Assert
//        assertEquals("BH-0002", res.getHoteles().get(1).getHotelCode());
//    }
//
//    @Test
//    void getHotelAvailableDestinationNoExist(){
//        //Arrange
//        when(repository.findAll()).thenReturn(list);
//        String fechaOrigin = "12/02/2022";
//        String fechaVuelta = "17/02/2022";
//        Date date1 = null, date2 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//        } catch (Exception e) {
//        }
//        DisponibilidadHotelDTO rsv = new DisponibilidadHotelDTO(date1, date2, "hhjghj");
//        //Assert
//        HotelesException ex =  assertThrows(HotelesException.class, () -> srvHotel.getHotelesDisponibles(rsv));
//        assertEquals("El destino elegido no existe",ex.getERROR());
//    }
//
//
//    @Test
//    void getHotelAvailableDateNo() throws FechasException, HotelesException {
//        //Arrange
//        when(repository.findAll()).thenReturn(list);
//        String fechaOrigin = "17/02/2022";
//        String fechaVuelta = "12/02/2022";
//        Date date1 = null, date2 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//        } catch (Exception e) {
//        }
//        System.out.printf(repository.findAll().get(0).getHotelCode());
//        System.out.printf(repository.findAll().get(1).getHotelCode());
//        DisponibilidadHotelDTO rsv = new DisponibilidadHotelDTO(date1, date2, "Buenos Aires");
//        //Assert
//        FechasException ex = assertThrows(FechasException.class, () -> srvHotel.getHotelesDisponibles(rsv));
//        assertEquals("La fecha de salida debe ser mayor a la de entrada",ex.getERROR());
//    }
//
//    @Test
//    void getHotelAvailableExist() throws FechasException, HotelesException {
//        //Arrange
//        when(repository.findAll()).thenReturn(list);
//        String fechaOrigin = "12/02/2024";
//        String fechaVuelta = "13/02/2024";
//        Date date1 = null, date2 = null;
//        try {
//            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaOrigin);
//            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaVuelta);
//        } catch (Exception e) {
//        }
//        DisponibilidadHotelDTO rsv = new DisponibilidadHotelDTO(date1, date2, "Buenos Aires");
//        //Assert
//        assertThrows(HotelesException.class, () -> srvHotel.getHotelesDisponibles(rsv));
//    }
//
//    @Test
//    void deleteHotel() throws HotelesException {
//        when(repository.findAll()).thenReturn(list);
//        HotelResponseDTO res = srvHotel.deleteHotel("BH-0002");
//        assertTrue(res.getMessage()== "Hotel borrado correctamente");
//    }
//    @Test
//    void deleteHotelNoExist(){
//        //Arrange
//        when(repository.findAll()).thenReturn(list);
//        HotelesException ex =  assertThrows(HotelesException.class, () -> srvHotel.deleteHotel("dfgdsfgsdfg"));
//        //Assert
//        assertEquals("No existe hotel con ese codigo",ex.getERROR());
//    }
//
//}
//
