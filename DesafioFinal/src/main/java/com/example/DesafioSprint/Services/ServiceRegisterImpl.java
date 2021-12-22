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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class ServiceRegisterImpl implements IServiceRegister {

    private final IFligthReservationRepository reservationRepository;

    private final IBookingRepository bookingRepository;

    public ServiceRegisterImpl(IFligthReservationRepository reservationRepository, IBookingRepository bookingRepository) {
        this.reservationRepository = reservationRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public DailyRegisterResponseDTO getDailyAmount(String date) throws ParseException {
        DailyRegisterResponseDTO response = new DailyRegisterResponseDTO();
        Double total = 0.0;
        for(ReservaVuelo f: reservationRepository.findAll()) {
            String db_date = new SimpleDateFormat("dd/MM/yyyy").format(f.getDateFrom());
            System.out.println("f.getDateFrom() =" + db_date);
            if(db_date.equals(date)) {
                total += f.getTotal();
                System.out.println("Total = " + total);
            }
        }
        for(Booking b: bookingRepository.findAll()) {
            String db_date = new SimpleDateFormat("dd/MM/yyyy").format(b.getDateFrom());
            System.out.println("b.getDateFrom() = " + db_date);
            if(db_date.equals(date)) {
                total += b.getTotal();
                System.out.println("Total = " + total);
            }
        }

        response.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        response.setTotal_income(total);
        return response;
    }

    @Override
    public MonthlyRegisterResponseDTO getMonthlyAmount(Integer month, Integer year) {
        MonthlyRegisterResponseDTO response = new MonthlyRegisterResponseDTO();
        response.setMonth(month);
        response.setYear(year);
        double total = 0.0;
        for(ReservaVuelo f: reservationRepository.findAll()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(f.getDateFrom());
            int db_month = calendar.get(Calendar.MONTH) + 1;
            System.out.println("Reservations month = " + db_month);
            int db_year = calendar.get(Calendar.YEAR);
            System.out.println("Reservations year = " + db_year);
            if(db_month == month && db_year == year) {
                total += f.getTotal();
                System.out.println("Total reservations = " + total);
            }
        }
        for(Booking b: bookingRepository.findAll()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(b.getDateFrom());
            int db_month = calendar.get(Calendar.MONTH) + 1;
            System.out.println("Bookings month = " + db_month);
            int db_year = calendar.get(Calendar.YEAR);
            System.out.println("Bookings year = " + db_year);
            if(db_month == month && db_year == year) {
                total += b.getTotal();
                System.out.println("Total bookings = " + total);
            }
        }
        response.setTotal_income(total);
        return response;
    }
}
