package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Repository.IRepositoryData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Test
    void listarHotelesTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hoteles[0].hotelCode").value("CH-0002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hoteles[1].hotelCode").value("CH-0003"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hoteles[2].hotelCode").value("HB-0001"));
    }

    @Test
    void listarHotelesConDatosTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "10/02/2022")
                        .param("dateTo", "02/03/2022")
                        .param("destination", "Puerto Iguazú")
                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hoteles[0].hotelCode").value("CH-0002"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.hoteles[1].hotelCode").value("CH-0003"));
    }

    @Test
    void listarHotelesConDestinoMalTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "10/02/2022")
                        .param("dateTo", "02/03/2022")
                        .param("destination", "gfhdfghdfgh")
                )
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("El destino elegido no existe"));
    }

    @Test
    void listarHotelesFaltanDatosTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "")
                        .param("dateTo", "")
                        .param("destination", "Buenos Aires")
                )
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Faltan Parametros para realizar la consulta"));
    }

    @Test
    void listarVuelosTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].flightNumber").value("BAPI-1235"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].flightNumber").value("PIBA-1420"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].flightNumber").value("PIBA-1420"));
    }

    @Test
    void listarVuelosConDatosTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "10/02/2022")
                        .param("dateTo", "23/02/2022")
                        .param("origin", "Tucumán")
                        .param("destination", "Puerto Iguazú")
                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].flightNumber").value("TUPI-3369"));
    }

    @Test
    void listarVuelosConDatosMalTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "10/02/2022")
                        .param("dateTo", "23/02/2022")
                        .param("origin", "Tucumán")
                        .param("destination", "Puerto gfhdfghdfghdf")
                )
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("El destino elegido no existe"));
    }

    @Test
    void listarVuelosFaltanDatosTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "")
                        .param("dateTo", "")
                        .param("origin", "Tucumán")
                        .param("destination", "Buenos Aires")
                )
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Faltan Parametros para realizar la consulta"));
    }

    @Test
    void addReservaHotelTest() throws Exception {
        String fechaCumple = "18/09/1995";
        String fechaFrom = "12/02/2022";
        String fechaTo = "14/02/2022";
        Date dateCumple = null, dateFrom = null, dateTo = null;
        try {
            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
        } catch (Exception e) {
        }
        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
        List<PersonaDTO> lista = new ArrayList<>();
        lista.add(persona1);
        ReservaDTO reserva = new ReservaDTO(dateFrom, dateTo, "Buenos Aires", "BH-0002", 1, "DOUBLE", lista);
        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
        ReservaHotelRequestDTO rsv = new ReservaHotelRequestDTO("maximiliano@hotmail.com", reserva, pago);

        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(rsv);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode.message").value("El proceso termino satisfactoriamente"));
    }

    @Test
    void addReservaVueloTest() throws Exception {
        //Arrange
        String fechaCumple = "18/09/1995";
        String fechaFrom = "10/02/2022";
        String fechaTo = "23/02/2022";
        Date dateCumple = null, dateFrom = null, dateTo = null;
        try {
            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
        } catch (Exception e) {
        }
        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
        List<PersonaDTO> lista = new ArrayList<>();
        lista.add(persona1);
        lista.add(persona2);
        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369", 2, "Economy", lista);
        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);

        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(reserva);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/flight-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode.code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode.message").value("El proceso termino satisfactoriamente"));
    }
    @Test
    void addReservaVueloCorreoMalTest() throws Exception {
        //Arrange
        String fechaCumple = "18/09/1995";
        String fechaFrom = "10/02/2022";
        String fechaTo = "23/02/2022";
        Date dateCumple = null, dateFrom = null, dateTo = null;
        try {
            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
        } catch (Exception e) {
        }
        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
        List<PersonaDTO> lista = new ArrayList<>();
        lista.add(persona1);
        lista.add(persona2);
        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369", 2, "Economy", lista);
        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano", rsv, pago);

        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(reserva);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/flight-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("DTO Erroneo"));
    }

    @Test
    void addReservaVueloCodigoMalTest() throws Exception {
        //Arrange
        String fechaCumple = "18/09/1995";
        String fechaFrom = "10/02/2022";
        String fechaTo = "23/02/2022";
        Date dateCumple = null, dateFrom = null, dateTo = null;
        try {
            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
        } catch (Exception e) {
        }
        PersonaDTO persona1 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
        PersonaDTO persona2 = new PersonaDTO("12345678", "Maximiliano", "Nicoletta", dateCumple, "maxinicoletta@hotmail.com");
        List<PersonaDTO> lista = new ArrayList<>();
        lista.add(persona1);
        lista.add(persona2);
        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369dgfhgfh", 2, "Economy", lista);
        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);

        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(reserva);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/flight-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No existe un Vuelo con ese Codigo"));
    }

    @Test
    void addReservaVueloValidacionPeopleTest() throws Exception {
        //Arrange
        String fechaCumple = "18/09/1995";
        String fechaFrom = "10/02/2022";
        String fechaTo = "23/02/2022";
        Date dateCumple = null, dateFrom = null, dateTo = null;
        try {
            dateCumple = new SimpleDateFormat("dd/MM/yyyy").parse(fechaCumple);
            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(fechaFrom);
            dateTo = new SimpleDateFormat("dd/MM/yyyy").parse(fechaTo);
        } catch (Exception e) {
        }
        List<PersonaDTO> lista = new ArrayList<>();
        ReservaVueloDTO rsv = new ReservaVueloDTO(dateFrom, dateTo, "Tucumán", "Puerto Iguazú", "TUPI-3369", 0, "Economy", lista);
        PagoDTO pago = new PagoDTO("CREDIT", "132456-456546-48", 6);
        ReservasVueloRequestDTO reserva = new ReservasVueloRequestDTO("maximiliano@hotmail.com", rsv, pago);

        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(reserva);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/flight-reservation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("DTO Erroneo"));
    }

    @Test
    void getReservasVuelos() throws Exception {
        addReservaVueloTest();
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flight-history/{cod}", "TUPI-3369"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("maximiliano@hotmail.com"));
    }


    @Test
    void getReservasHoteles() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/bookings-history/{cod}", "BH-0002"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userName").value("maximiliano@hotmail.com"));
    }

}