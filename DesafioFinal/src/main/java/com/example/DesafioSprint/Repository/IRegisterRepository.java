package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IRegisterRepository extends JpaRepository<Reserva, Long> {
    @Query("SELECT SUM(R.total) FROM Reserva R WHERE R.dateFrom = :date")
    Double getDailyAmount(@Param("date") Date date);

    @Query("SELECT (R.total) FROM Reserva R WHERE month(R.dateFrom) = month(:month) AND year(R.dateFrom) = year(:year)")
    Double getMonthlyAmount(@Param("month") int month, @Param("year") int year);
}
