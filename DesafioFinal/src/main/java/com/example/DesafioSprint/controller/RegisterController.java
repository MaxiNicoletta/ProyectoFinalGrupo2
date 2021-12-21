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

    /**
     * Endpoint that returns the total amount of earnings from the reservations in a given day.
     * @param rawDate Day from which we obtain the total amount of earnings.
     * @return DailyRegisterResponseDTO, containing the date and the total amount of that date.
     * @throws ParseException
     */
    @GetMapping
    public DailyRegisterResponseDTO returnDailyAmount(@RequestParam String rawDate) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
        return service.getDailyAmount(date);
    }

    /**
     * Returns the total amount of earnings given a month and a year.
     * @param month Month from where I want to get the earnings.
     * @param year Year from where I want to get the earnings.
     * @return MonthlyRegisterResponseDTO, containing the month, year and the total amount of that date.
     */
    @GetMapping
    public MonthlyRegisterResponseDTO returnMonthlyAmount(@Valid @RequestParam @Min(1) @Max(12) int month, @RequestParam int year){
        return service.getMonthlyAmount(month, year);
    }
}
