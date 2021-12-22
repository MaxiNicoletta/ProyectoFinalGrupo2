package com.example.DesafioSprint.Services;


import com.example.DesafioSprint.DTOs.DailyRegisterResponseDTO;
import com.example.DesafioSprint.DTOs.MonthlyRegisterResponseDTO;

import java.text.ParseException;
import java.util.Date;

public interface IServiceRegister {

    DailyRegisterResponseDTO getDailyAmount(String date) throws ParseException;

    MonthlyRegisterResponseDTO getMonthlyAmount(Integer month, Integer year);
}
