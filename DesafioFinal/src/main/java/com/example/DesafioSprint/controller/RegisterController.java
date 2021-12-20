package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.DailyRegisterResponseDTO;
import com.example.DesafioSprint.DTOs.MonthlyRegisterResponseDTO;
import com.example.DesafioSprint.Services.IServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/income")
public class RegisterController {

   @Autowired
    IServiceRegister service;

    @GetMapping
    public DailyRegisterResponseDTO returnDailyAmount(@RequestParam String rawDate) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        return service.getDailyAmount(date);
    }

    @GetMapping
    public MonthlyRegisterResponseDTO returnMonthlyAmount(@Valid @RequestParam @Min(1) @Max(12) int month, @RequestParam int year){
        return service.getMonthlyAmount(month, year);
    }
}
