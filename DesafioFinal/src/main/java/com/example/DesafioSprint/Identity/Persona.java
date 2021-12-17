package com.example.DesafioSprint.Identity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private String dni;
    private String name;
    private String lastname;
    private Date birthDate;
    private String mail;

}
