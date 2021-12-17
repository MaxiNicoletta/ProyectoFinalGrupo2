package com.example.DesafioSprint.Edentity;

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
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    protected String userName;
    protected Date dateFrom;
    protected Date dateTo;
    protected String destination;
    @OneToMany(mappedBy = "id")
    protected List<Persona> people;
    @OneToOne
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


