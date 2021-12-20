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


    public PersonaDTO entityToDTO(Persona person){
        return new PersonaDTO(
                person.getDni(),
                person.getName(),
                person.getLastname(),
                person.getBirthDate(),
                person.getMail()
        );
    }

    public Persona dtoToEntity(PersonaDTO person){
        return new Persona(
          person.getDni(),
          person.getName(),
          person.getLastname(),
          person.getBirthDate(),
          person.getMail()
        );
    }

}
