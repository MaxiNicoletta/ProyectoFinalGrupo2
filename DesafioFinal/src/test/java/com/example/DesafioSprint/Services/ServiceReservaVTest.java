//package com.example.DesafioSprint.Services;
//
//import com.example.DesafioSprint.DTOs.*;
//import com.example.DesafioSprint.Exceptions.*;
//import com.example.DesafioSprint.Repository.IFlightRepository;
//import com.example.DesafioSprint.Repository.FlightRepository;
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
//@ExtendWith(MockitoExtension.class)
//class ServiceReservaVTest {
//    @Mock
//    IFlightRepository repository;
//
//    @InjectMocks
//    ServiceReservaV srvVueloR;
//
//    @BeforeEach
//    void cargarServ() {
//        srvVueloR = new ServiceReservaV(srvVueloR, repository);
//        repository = new FlightRepository();
//    }
//
//    @Test
//    void addReservaVueloDestinationMalTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "15/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Buenos Aires", "Puerto Iguazúgfhdfghdfgh", "BAPI-1235", 1, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        assertThrows(UbicacionException.class, () -> srvVueloR.addReserva(reserva));
//    }
//
//    @Test
//    void addReservaVueloOriginMalTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "15/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Buenos Aireshgfjghfjgfj", "Puerto Iguazú", "BAPI-1235", 1, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        assertThrows(UbicacionException.class, () -> srvVueloR.addReserva(reserva));
//    }
//
//    @Test
//    void addReservaVueloCantidadPersonasMalTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "15/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Buenos Aires", "Puerto Iguazú", "BAPI-1235", 1, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        assertThrows(PersonasException.class, () -> srvVueloR.addReserva(reserva));
//    }
//
//    @Test
//    void addReservaVueloVueloNoExisteTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "15/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Buenos Aires", "Puerto Iguazú", "BAPI-1235dfgdfg", 2, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        assertThrows(VuelosException.class, () -> srvVueloR.addReserva(reserva));
//    }
//
//    @Test
//    void addReservaVueloTest() throws FechasException, PersonasException, UbicacionException, VuelosException {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "23/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369", 2, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        ReservaVueloResponseDTO res = srvVueloR.addReserva(reserva);
//        assertTrue(res.getUserName().equals("maximiliano@hotmail.com"));
//    }
//
//    @Test
//    void addReservaVuelo2CuotasTest() throws FechasException, PersonasException, UbicacionException, VuelosException {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "23/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369", 2, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 2);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        ReservaVueloResponseDTO res = srvVueloR.addReserva(reserva);
//        assertTrue(res.getUserName().equals("maximiliano@hotmail.com"));
//    }
//
//    @Test
//    void addReservaVueloFechasNoDisponibleTest() throws FechasException, PersonasException, UbicacionException, VuelosException {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "23/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369", 2, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 2);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        ReservaVueloResponseDTO res = srvVueloR.addReserva(reserva);
//        assertTrue(res.getUserName().equals("maximiliano@hotmail.com"));
//    }
//
//    @Test
//    void addReservaVueloDebitoNoTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "23/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369", 2, "Economy", lista);
//        PagoDTO pago = new PagoDTO("DEBIT", "132456-456546-48", 6);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        assertThrows(VuelosException.class, () -> srvVueloR.addReserva(reserva));
//    }
//
//
//    @Test
//    void getReservasVuelolNoExistenteTest(){
//        assertThrows(VuelosException.class, () -> srvVueloR.getReservasVuelo("gdfkjsgdfg"));
//    }
//    @Test
//    void getReservasHotelVacioTest(){
//        assertThrows(VuelosException.class, () -> srvVueloR.getReservasVuelo("BATU-5536"));
//    }
//
//    @Test
//    void getReservasHotelExistosoTest() throws UbicacionException, FechasException, VuelosException, PersonasException {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "23/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369", 2, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        ReservaVueloResponseDTO reservaResponse = srvVueloR.addReserva(reserva);
//        List<ReservaVueloResponseDTO> res = srvVueloR.getReservasVuelo("TUPI-3369");
//        assertTrue(res.get(0).getUserName().equals("maximiliano@hotmail.com"));
//    }
//
//    @Test
//    void addReservaVueloTestNo() throws FechasException, PersonasException, UbicacionException, VuelosException {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "10/02/2022";
//        String fechaTo = "23/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "BAPI-1235", 2, "Economy", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);
//        VuelosException ex = assertThrows(VuelosException.class, () -> srvVueloR.addReserva(reserva));
//        assertEquals("Ese vuelo No esta disponible en las fechas ingresadas",ex.getERROR());
//    }
//}

