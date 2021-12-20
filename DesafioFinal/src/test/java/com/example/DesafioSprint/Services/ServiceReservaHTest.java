//package com.example.DesafioSprint.Services;
//
//import com.example.DesafioSprint.DTOs.*;
//import com.example.DesafioSprint.Exceptions.FechasException;
//import com.example.DesafioSprint.Exceptions.HotelesException;
//import com.example.DesafioSprint.Exceptions.PersonasException;
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
//
//@ExtendWith(MockitoExtension.class)
//class ServiceReservaHTest {
//    @Mock
//    IFlightRepository repository;
//
//    @InjectMocks
//    ServiceBooking srvHotelR;
//
//    @BeforeEach
//    void cargarServ() {
//        repository = new FlightRepository();
//        srvHotelR = new ServiceBooking();
//    }
//
//    @Test
//    void addReservaDestinationMalTest() {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Airesgjhjghj", "BH-0002", 1, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO res = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        assertThrows(HotelesException.class, () -> srvHotelR.addReserva(res));
//    }
//
//    @Test
//    void addReservaCodigoHotelMalTest() {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002ghjfghj", 1, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO res = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        assertThrows(HotelesException.class, () -> srvHotelR.addReserva(res));
//    }
//
//    @Test
//    void addReservaPersonasMalTest() {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano2", "Nicoletta2", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 1, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO res = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        assertThrows(PersonasException.class, () -> srvHotelR.addReserva(res));
//    }
//
//    @Test
//    void addReservaPersonasMalHabitacionSingleTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano2", "Nicoletta2", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 2, "SINGLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO res = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        assertThrows(PersonasException.class, () -> srvHotelR.addReserva(res));
//    }
//
//    @Test
//    void addReservaPersonasMalHabitacionDobleTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano2", "Nicoletta2", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona3 = new PersonaDTO("12345678", "Maximiliano2", "Nicoletta2", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        lista.add(persona3);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 3, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO res = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        assertThrows(PersonasException.class, () -> srvHotelR.addReserva(res));
//    }
//
//    @Test
//    void addReservaPersonasMalHabitacionTripleTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano2", "Nicoletta2", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona3 = new PersonaDTO("12345678", "Maximiliano3", "Nicoletta2", dateCumple, "maxinicoletta@hotmail.com");
//        PersonaDTO persona4 = new PersonaDTO("12345678", "Maximiliano4", "Nicoletta2", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        lista.add(persona2);
//        lista.add(persona3);
//        lista.add(persona4);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 4, "TRIPLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO res = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        assertThrows(PersonasException.class, () -> srvHotelR.addReserva(res));
//    }
//
//    @Test
//    void addReservaTest() throws FechasException, HotelesException, PersonasException {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 1, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO rsv = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        BookingResponseDTO res = srvHotelR.addReserva(rsv);
//        assertEquals(200 , res.getStatusCode().getCode());
//    }
//
//    @Test
//    void addReserva2vecesTest() throws FechasException, HotelesException, PersonasException {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 1, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO rsv = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        srvHotelR.addReserva(rsv);
//
//        //Arrange
//        String fechaCumple2 = "18/09/1995";
//        String fechaFrom2 = "16/02/2022";
//        String fechaTo2 = "17/02/2022";
//        Date dateCumple2 = null, dateFrom2 = null, dateTo2 = null;
//        try {
//            dateCumple2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple2);
//            dateFrom2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom2);
//            dateTo2 = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo2);
//        } catch (Exception e2) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple2, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista2 = new ArrayList<>();
//        lista2.add(persona2);
//        BookingDTO reserva2 = new BookingDTO(dateFrom2, dateTo2, "Buenos Aires", "BH-0002", 1, "DOUBLE", lista2);
//        PagoDTO pago2 = new PagoDTO("CREDIT", "132456-456546-48", 2);
//        BookingRequestDTO rsv2 = new BookingRequestDTO("maximiliano2@hotmail.com", reserva2, pago2);
//        BookingResponseDTO res2 = srvHotelR.addReserva(rsv2);
//        assertEquals(200,res2.getStatusCode().getCode());
//    }
//
//    @Test
//    void addReservaDebitErrorTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 1, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("DEBIT", "132456-456546-48", 6);
//        BookingRequestDTO rsv = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        assertThrows(HotelesException.class, () -> srvHotelR.addReserva(rsv));
//    }
//
//    @Test
//    void addReservaExecpcionHotelTest(){
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "11/02/2022";
//        String fechaTo = "17/03/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 1, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO rsv = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        assertThrows(HotelesException.class, () -> srvHotelR.addReserva(rsv));
//    }
//
//
//    @Test
//    void getReservasHotelNoExistenteTest(){
//        assertThrows(HotelesException.class, () -> srvHotelR.getReservasHotel("gdfkjsgdfg"));
//    }
//
//    @Test
//    void getReservasHotelVacioTest(){
//        assertThrows(HotelesException.class, () -> srvHotelR.getReservasHotel("HB-0001"));
//    }
//
//    @Test
//    void getReservasHotelTest() throws HotelesException, FechasException, PersonasException {
//        //Arrange
//        String fechaCumple = "18/09/1995";
//        String fechaFrom = "12/02/2022";
//        String fechaTo = "14/02/2022";
//        Date dateCumple = null, dateFrom = null, dateTo = null;
//        try {
//            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
//            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
//            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
//        } catch (Exception e) {
//            System.out.println("Error en la transformacion de fechas");
//        }
//        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
//        List<PersonaDTO> lista = new ArrayList<>();
//        lista.add(persona1);
//        BookingDTO reserva = new BookingDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 1, "DOUBLE", lista);
//        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
//        BookingRequestDTO rsv = new BookingRequestDTO("maximiliano@hotmail.com", reserva, pago);
//        srvHotelR.addReserva(rsv);
//        //Act
//        List<BookingResponseDTO> reservas = srvHotelR.getReservasHotel("BH-0002");
//        assertEquals("maximiliano@hotmail.com",reservas.get(0).getUserName());
//    }
//}