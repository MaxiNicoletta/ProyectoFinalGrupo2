package com.example.DesafioSprint.Edentity;

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

}
