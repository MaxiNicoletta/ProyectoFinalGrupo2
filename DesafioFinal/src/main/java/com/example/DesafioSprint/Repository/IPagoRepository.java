package com.example.DesafioSprint.Repository;

import com.example.DesafioSprint.Entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagoRepository extends JpaRepository<Pago,Long> {
}
