package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoRepository extends JpaRepository<Pago,Long> {
}