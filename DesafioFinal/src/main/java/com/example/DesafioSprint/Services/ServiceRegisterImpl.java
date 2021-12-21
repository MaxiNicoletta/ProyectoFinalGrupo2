package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.MonthlyRegisterResponseDTO;
import com.example.DesafioSprint.DTOs.DailyRegisterResponseDTO;
import com.example.DesafioSprint.Repository.IRegisterRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceRegisterImpl implements IServiceRegister {

    private final IRegisterRepository repository;

    public ServiceRegisterImpl(IRegisterRepository repository) {
        this.repository = repository;
    }

    @Override
    public DailyRegisterResponseDTO getDailyAmount(Date date) {
        DailyRegisterResponseDTO response = new DailyRegisterResponseDTO();
        response.setDate(date);
        response.setTotal_income(repository.getDailyAmount(date));
        return response;
    }

    @Override
    public MonthlyRegisterResponseDTO getMonthlyAmount(int month, int year) {
        MonthlyRegisterResponseDTO response = new MonthlyRegisterResponseDTO();
        response.setMonth(month);
        response.setYear(year);
        response.setTotal_income(repository.getMonthlyAmount(month, year));
        return response;
    }
}
