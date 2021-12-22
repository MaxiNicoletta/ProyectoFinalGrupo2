package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.HotelRequestDTO;
import com.example.DesafioSprint.DTOs.VueloRequestDTO;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerFlightTest {
    @Autowired
    MockMvc mockMvc;


    @Test
    void addFlightNo() throws Exception {
        VueloRequestDTO vuelo = new VueloRequestDTO("BAPI-1235","vuelo 1","Buenos Aires","Puerto Iguazú","Economy",6500,"10/02/2022","15/02/2022");
        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(vuelo);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/flights/new/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Ya existe un vuelo con ese numero"));
    }

    @Test
    void flightGet() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "10/02/2022")
                        .param("dateTo", "15/02/2022")
                        .param("origin", "Buenos Aires")
                        .param("destination", "Puerto Iguazú")
                )
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].flightNumber").value("BAPI-1235"));
    }

    @Test
    void flightUpdateData() throws Exception {
        VueloRequestDTO vuelo = new VueloRequestDTO("BAPI-1235","vuelo 1 Modificado","Buenos Aires","Puerto Iguazú","Economy",6500,"10/02/2022","15/02/2022");
        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(vuelo);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/flights/edit/")
                        .param("flightNumber","BAPI-1235")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Vuelo modificado correctamente"));

    }
    @Test
    void flightGetSolo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].flightNumber").value("BAPI-1235"));
    }


    @Test
    void flightGetParam() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/flights")
                        .param("dateFrom", "10/02/2022")
                        .param("dateTo", "")
                        .param("origin", "Buenos Aires")
                        .param("destination", "Puerto Iguazú")
                )
                .andDo(print()).andExpect(status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Faltan Parametros para realizar la consulta"));
    }
    @Test
    void addFlightOK() throws Exception {
        VueloRequestDTO vuelo = new VueloRequestDTO("BAPI-1235","vuelo 1","Buenos Aires","Puerto Iguazú","Economy",6500,"10/02/2022","15/02/2022");
        ResponseEntity<?> responseDto = ResponseEntity.ok(null);
        ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false).writer();
        String payloadJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(vuelo);
        String responseJson = ((com.fasterxml.jackson.databind.ObjectWriter) writer).writeValueAsString(responseDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/flights/new/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Vuelo dado de alta correctamente"));
    }

    @Test
    void flightDelete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/flights/delete/")
                        .param("flightNumber", "BAPI-1235"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Vuelo borrado correctamente"));
    }






}
