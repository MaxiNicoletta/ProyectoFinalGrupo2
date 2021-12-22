package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.MonthlyRegisterResponseDTO;
import com.example.DesafioSprint.DTOs.DailyRegisterResponseDTO;
import com.example.DesafioSprint.Entities.Booking;
import com.example.DesafioSprint.Entities.Reserva;
import com.example.DesafioSprint.Entities.ReservaVuelo;
import com.example.DesafioSprint.Repository.IBookingRepository;
import com.example.DesafioSprint.Repository.IFligthReservationRepository;
import com.example.DesafioSprint.Repository.IRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class ServiceRegisterImpl implements IServiceRegister {

    @PersistenceContext
    private EntityManager entityManager;

    private final IFligthReservationRepository reservationRepository;

    private final IBookingRepository bookingRepository;

    private final IRegisterRepository repository;

    public ServiceRegisterImpl(IFligthReservationRepository reservationRepository, IBookingRepository bookingRepository, IRegisterRepository repository) {
        this.reservationRepository = reservationRepository;
        this.bookingRepository = bookingRepository;
        this.repository = repository;
    }

    @Override
    public DailyRegisterResponseDTO getDailyAmount(Date date) {
        DailyRegisterResponseDTO response = new DailyRegisterResponseDTO();
        Double total = 0.0;
        for(ReservaVuelo f: reservationRepository.findAll()) {
            if(f.getDateFrom().equals(date));
                total += f.getTotal();
        }
        for(Booking b: bookingRepository.findAll()) {
            if(b.getDateFrom().equals(date))
                total += b.getTotal();
        }
        response.setDate(date);
        response.setTotal_income(total);
        return response;
    }

    @Override
    public MonthlyRegisterResponseDTO getMonthlyAmount(Integer month, Integer year) {
        MonthlyRegisterResponseDTO response = new MonthlyRegisterResponseDTO();
        response.setMonth(month);
        response.setYear(year);
        return response;
    }
}
