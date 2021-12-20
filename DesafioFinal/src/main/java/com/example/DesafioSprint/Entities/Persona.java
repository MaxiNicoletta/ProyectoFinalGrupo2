package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.PersonaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @ManyToMany(mappedBy = "people",cascade = CascadeType.ALL)
    private List<Reserva> booking;

    public Persona(String dni, String name, String lastname, Date birthDate, String mail) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.mail = mail;
    }

    public PersonaDTO personaToDTO(){
        return new PersonaDTO(
                getDni(),
                getName(),
                getLastname(),
                getBirthDate(),
                getMail()
        );
    }

    public PersonaDTO entityToDTO(Persona person){
        return new PersonaDTO(
                person.getDni(),
                person.getName(),
                person.getLastname(),
                person.getBirthDate(),
                person.getMail()
        );
    }

    public Persona DTOPersonaToDTO(PersonaDTO person){
        return new Persona(
          person.getDni(),
          person.getName(),
          person.getLastname(),
          person.getBirthDate(),
          person.getMail()
        );
    }

}
