package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Entities.Pago;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerHotelTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void addHotelOk() throws Exception {
        HotelRequestDTO hotel = new HotelRequestDTO("BH-0002","hotel1","Buenos Aires","Double",12,"12/02/2022","17/02/2022",false);
        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(hotel);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/hotels/new/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Hotel dado de alta correctamente"));
    }
    @Test
    void noAddHotel() throws Exception {
        HotelRequestDTO hotel = new HotelRequestDTO("BH-0002","hotel1","Buenos Aires","Double",12,"12/02/2022","17/02/2022",false);
        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(hotel);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/hotels/new/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Ya existe un hotel con ese codigo"));
    }


    @Test
    void listarHotelesConDatosTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "13/02/2022")
                        .param("dateTo", "14/02/2022")
                        .param("destination", "Buenos Aires")
                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hoteles[0].hotelCode").value("BH-0002"));
    }
    @Test
    void listarHotelesConDatosFaltantastesTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels")
                        .param("dateFrom", "13/02/2022")
                        .param("dateTo", "")
                        .param("destination", "Buenos Aires")
                )
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Faltan Parametros para realizar la consulta"));
    }


    @Test
    void listarHotelesTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hotels"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.hoteles[0].hotelCode").value("BH-0002"));
    }
    @Test
    void updatedTest() throws Exception {

        HotelRequestDTO hotel = new HotelRequestDTO("BH-0002","hotelModificado","Buenos Aires","Double",12,"12/02/2022","17/02/2022",false);
        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(hotel);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/hotels/edit/")
                        .param("hotelCode", "BH-0002")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Hotel modificado correctamente"));
    }
    @Test
    void deleteTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/hotels/delete/")
                        .param("hotelCode", "BH-0002"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Hotel borrado correctamente"));
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
}