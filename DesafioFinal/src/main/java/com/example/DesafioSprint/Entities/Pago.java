package com.example.DesafioSprint.Entities;

import com.example.DesafioSprint.DTOs.PagoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pago")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String number;
    private double dues;

    public Pago(String type, String number, double dues){
        this.type = type;
        this.number = number;
        this.dues = dues;
    }

    public PagoDTO paymentToDTO(){
        return new PagoDTO(getType(),getNumber(),getDues());
    }

    public Pago paymentDTOtoPayment(PagoDTO paymentDTO){
        return new Pago(id,paymentDTO.getType(),paymentDTO.getNumber(),paymentDTO.getDues());
    }


}
