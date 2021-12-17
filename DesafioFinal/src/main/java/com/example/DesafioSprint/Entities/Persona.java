package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.PersonaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Persona {
    @Id
    private String dni;
    private String name;
    private String lastname;
    private Date birthDate;
    private String mail;

    public PersonaDTO personaToDTO(){
        return new PersonaDTO(getDni(),getName(),getLastname(),getBirthDate(),getMail());
    }
    public Persona personaDTOtoPersona(PersonaDTO personaDTO){
        return new Persona(personaDTO.getDni(),personaDTO.getName(),personaDTO.getLastname(),personaDTO.getBirthDate(),personaDTO.getMail());
    }
}
