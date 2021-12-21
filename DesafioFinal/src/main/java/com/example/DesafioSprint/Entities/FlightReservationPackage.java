//package com.example.DesafioSprint.Entities;
//
//import com.example.DesafioSprint.DTOs.FlightReservationPackageDTO;
//import com.example.DesafioSprint.DTOs.ReservaVueloDTO;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class FlightReservationPackage {
//    private ReservaVuelo firstFlightReservation;
//    private ReservaVuelo secondFlightReservation;
//
//    public FlightReservationPackageDTO packageToDTO(){
//        ReservaVueloDTO firstFlightReservationDTO = firstFlightReservation.entityToDTO();
//        ReservaVueloDTO secondFlightReservationDTO =  secondFlightReservation.entityToDTO();
//        return new FlightReservationPackageDTO(firstFlightReservationDTO,secondFlightReservationDTO);
//    }
//
//    public FlightReservationPackage DTOtoPackage(ReservaVueloDTO firstPackageDTO, ReservaVueloDTO secondPackageDTO){
//        ReservaVuelo firstFlightReservation = new ReservaVuelo();
//        ReservaVuelo secondFlightReservation = new ReservaVuelo();
//        firstFlightReservation.dtoToEntity(firstPackageDTO);
//        secondFlightReservation.dtoToEntity(secondPackageDTO);
//        return new FlightReservationPackage(firstFlightReservation,secondFlightReservation);
//    }
////
//
//
//
//}
