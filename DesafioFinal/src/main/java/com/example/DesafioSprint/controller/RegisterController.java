package com.example.DesafioSprint.controller;

import com.example.DesafioSprint.DTOs.RegisterResponseDTO;
import com.example.DesafioSprint.Services.IServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/income")
public class RegisterController {

   @Autowired
    IServiceRegister service;

    /**
     * Endpoint that returns the total amount of earnings from the reservations in a given a date.
     * @param rawDate Day from which we obtain the total amount of earnings.
     * @param month Month from where I want to get the earnings.
     * @param year Year from where I want to get the earnings.
     * @return RegisterResponseDTO, containing the date and the total amount of that date.
     * @throws ParseException
     */
    @GetMapping
    public RegisterResponseDTO returnDailyAmount(@RequestParam (required = false) String rawDate,
                                                 @Valid @RequestParam (required = false) @Min(1) @Max(12) Integer month,
                                                 @RequestParam (required = false) @Positive Integer year) throws ParseException {

        if(rawDate != null && !rawDate.equals("")) {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(rawDate);
            return service.getDailyAmount(date);
        } else {
            return service.getMonthlyAmount(month, year);
        }
    }
}
