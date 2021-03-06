package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.BookingDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String userName;
    @Column(name = "date_from")
    protected Date dateFrom;
    protected Date dateTo;
    protected String destination;
    @ManyToMany (cascade= CascadeType.ALL)
    @JoinTable(name = "reservation_people",
                joinColumns = @JoinColumn(name = "reservation_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name="people_id",referencedColumnName = "dni"))
    protected List<Persona> people ;
    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name ="pago_id")
    protected Pago paymentMethod;
    protected double amount;
    protected double interest;
    protected double total;

    public Reserva(String userName, Date dateFrom, Date dateTo, String destination, List<Persona> people, Pago paymentMethod, double amount, double interest, double total) {
        this.userName = userName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.destination = destination;
        this.people = people;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.interest = interest;
        this.total = total;
    }

}


