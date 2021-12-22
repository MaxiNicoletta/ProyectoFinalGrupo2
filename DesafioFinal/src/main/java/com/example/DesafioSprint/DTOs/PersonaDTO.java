package com.example.DesafioSprint.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private String dni;
    private String name;
    private String lastname;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-3")
    private Date birthDate;
    @Email(message = "Por favor ingrese un e-mail válido")
    private String mail;

}
