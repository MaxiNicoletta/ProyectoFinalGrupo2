package com.example.DesafioSprint.DTOs;

import com.example.DesafioSprint.Entities.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TouristicPackageDTO {
    private int packageNumber;
    private String name;
    private String creation_date;
    private int client_Id;
    private int bookResId1;
    private int bookResId2;

}
