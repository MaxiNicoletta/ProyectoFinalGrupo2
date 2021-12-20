package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IRegisterRepository extends JpaRepository<Reserva, Long> {
    @Query("")
    Double getDailyAmount(@Param("date") Date date);

    @Query()
    Double getMonthlyAmount(@Param("month") int month, @Param("year") int year);
}
