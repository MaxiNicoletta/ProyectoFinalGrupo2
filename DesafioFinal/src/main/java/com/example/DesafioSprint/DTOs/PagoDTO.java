package com.example.DesafioSprint.DTOs;


import com.example.DesafioSprint.Entities.Pago;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private String type;
    private String number;
    private double dues;


    public Pago paymentToDTO(){
        return new Pago(getType(),getNumber(),getDues());
    }
}
