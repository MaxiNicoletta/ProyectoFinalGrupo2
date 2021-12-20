package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Entities.Pago;
import com.example.DesafioSprint.Entities.Persona;
import com.example.DesafioSprint.Entities.Vuelo;
import com.example.DesafioSprint.Exceptions.*;
import com.example.DesafioSprint.Entities.ReservaVuelo;
import com.example.DesafioSprint.Repository.IFlightRepository;
import com.example.DesafioSprint.Repository.IFligthReservationRepository;
import com.example.DesafioSprint.Repository.IPagoRepository;
import com.example.DesafioSprint.Repository.IPersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceReservaV implements IServiceReservaV {

    private final IFligthReservationRepository repositoryReservation;
    private final IFlightRepository repositoryFlight;
    private final IPagoRepository repositoryPago;
    private final IServiceVuelo serviceVuelo;
    private final IPersonRepository repositoryPerson;

    public ServiceReservaV(IFligthReservationRepository repositoryReservation, IFlightRepository repositoryFlight, IPagoRepository repositoryPago, IServiceVuelo serviceVuelo, IPersonRepository repositoryPerson) {
        this.repositoryReservation = repositoryReservation;
        this.repositoryFlight = repositoryFlight;
        this.repositoryPago = repositoryPago;
        this.serviceVuelo = serviceVuelo;
        this.repositoryPerson = repositoryPerson;
    }

    @Override
    public FlightResponseDTO addReserva(ReservasVueloRequestDTO rsVuelo) throws PersonasException, VuelosException, FechasException, UbicacionException {
        if (!repositoryFlight.existsDestinationVuelo(rsVuelo.getFlightReservation().getDestination()))
            throw new UbicacionException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
        if (!repositoryFlight.existsOriginVuelo(rsVuelo.getFlightReservation().getOrigin()))
            throw new UbicacionException("El origen elegido no existe", HttpStatus.BAD_REQUEST);
        if (rsVuelo.getFlightReservation().getSeats() != rsVuelo.getFlightReservation().getPeople().size())
            throw new PersonasException("La cantidad de Personas no coincide", HttpStatus.BAD_REQUEST);
        if (!repositoryFlight.existsVuelo(rsVuelo.getFlightReservation().getFlightNumber()))
            throw new VuelosException("No existe un Vuelo con ese Codigo", HttpStatus.BAD_REQUEST);
        DisponibilidadVuelosDTO nuevo = new DisponibilidadVuelosDTO(rsVuelo.getFlightReservation().getGoingDate(), rsVuelo.getFlightReservation().getReturnDate(), rsVuelo.getFlightReservation().getOrigin(), rsVuelo.getFlightReservation().getDestination());
        List<VueloDTO> vuelosDisponibles = serviceVuelo.disponibilidadVuelos(nuevo);
        VueloDTO vuelo = null;
        for (VueloDTO v : vuelosDisponibles)
            if (v.getFlightNumber().equals(rsVuelo.getFlightReservation().getFlightNumber())) {
                vuelo = v;
                break;
            }
        if (vuelo != null) {
            List<Persona> l = new ArrayList<>();
            for (PersonaDTO p : rsVuelo.getFlightReservation().getPeople()) {
                Persona persona = new Persona(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
                l.add(persona);
                repositoryPerson.save(persona);
            }

            double interest = 0;
            double amount = vuelo.getFlightPrice() * rsVuelo.getFlightReservation().getSeats();
            double total = 0;
            if (rsVuelo.getPaymentMethod().getType().equals("CREDIT")) {
                if (rsVuelo.getPaymentMethod().getDues() <= 3) {
                    interest = 5;
                    total = amount * interest;
                } else if ((rsVuelo.getPaymentMethod().getDues() >= 3) && ((rsVuelo.getPaymentMethod().getDues() <= 6))) {
                    interest = 10;
                    total = amount * interest;
                }
            } else if (rsVuelo.getPaymentMethod().getType().equals("DEBIT")) {
                total = amount;
                if (rsVuelo.getPaymentMethod().getDues() != 1)
                    throw new VuelosException("Se ha ingresado una cantidad de cuotas diferente a 1 ", HttpStatus.BAD_REQUEST);
            }
            Pago pago = new Pago(rsVuelo.getPaymentMethod().getType(), rsVuelo.getPaymentMethod().getNumber(), rsVuelo.getPaymentMethod().getDues());
            repositoryPago.save(pago);
            ReservaVuelo rsv = new ReservaVuelo(rsVuelo.getUserName(), rsVuelo.getFlightReservation().getGoingDate(), rsVuelo.getFlightReservation().getReturnDate(), rsVuelo.getFlightReservation().getDestination(), l, pago, amount, interest, total, rsVuelo.getFlightReservation().getFlightNumber(), rsVuelo.getFlightReservation().getSeats(), rsVuelo.getFlightReservation().getSeatType(), rsVuelo.getFlightReservation().getDestination());
            repositoryReservation.save(rsv);
            return new FlightResponseDTO("Reserva de vuelo dada de alta correctamente.");
        } else
            throw new VuelosException("Ese vuelo No esta disponible en las fechas ingresadas", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ArrayList<ReservaVueloDTO> getAllReservations() {
        ArrayList<ReservaVueloDTO> response = new ArrayList<>();
        for(ReservaVuelo r: repositoryReservation.findAll())
            response.add(r.entityToDTO());
        return response;
    }

    @Override
    public FlightResponseDTO updateFlightReservation(Long id,ReservasVueloRequestDTO reservasVueloRequestDTO) throws VuelosException, PersonasException {
        validation(reservasVueloRequestDTO,id);
        ReservaVuelo rsv = repositoryReservation.getById(id);
        rsv.setUserName(reservasVueloRequestDTO.getUserName());
        if (rsv.getSeats()!= reservasVueloRequestDTO.getFlightReservation().getSeats())
           person(reservasVueloRequestDTO,rsv);
        else
            rsv.setSeats(reservasVueloRequestDTO.getFlightReservation().getSeats());
        if (rsv.getPaymentMethod() != reservasVueloRequestDTO.getFlightReservation().getParmentMethod())
            getPayment(reservasVueloRequestDTO,rsv);
        else{
                Pago pago = new Pago(reservasVueloRequestDTO.getPaymentMethod().getType(), reservasVueloRequestDTO.getPaymentMethod().getNumber(), reservasVueloRequestDTO.getPaymentMethod().getDues());
                rsv.setPaymentMethod(pago);
            }
            repositoryReservation.save(rsv);
        return new FlightResponseDTO("Reserva de vuelo modificada correctamente.");
        }

    @Override
    public FlightResponseDTO deleteFlightReservation(Long id) throws VuelosException {
        ReservaVuelo rsv = repositoryReservation.getById(id);
        if (rsv == null)
        throw new VuelosException("No existe hotel con ese codigo", HttpStatus.BAD_REQUEST);
        else {
            repositoryReservation.deleteById(id);
            repositoryPago.delete(rsv.getPaymentMethod());
            return new FlightResponseDTO("Reserva de vuelo eliminada correctamente.");
        }
    }

    private void getPayment(ReservasVueloRequestDTO reservasVueloRequestDTO, ReservaVuelo rsv) throws VuelosException {
        double interest = 0;
        Vuelo vuelo = repositoryFlight.findFlightByCod(reservasVueloRequestDTO.getFlightReservation().getFlightNumber());
        double amount = vuelo.getPricePerPerson() * reservasVueloRequestDTO.getFlightReservation().getSeats();
        double total = 0;
        if (reservasVueloRequestDTO.getPaymentMethod().getType().equals("CREDIT")) {
            if (reservasVueloRequestDTO.getPaymentMethod().getDues() <= 3) {
                interest = 5;
                total = amount * interest;
            } else if ((reservasVueloRequestDTO.getPaymentMethod().getDues() >= 3) && ((reservasVueloRequestDTO.getPaymentMethod().getDues() <= 6))) {
                interest = 10;
                total = amount * interest;
            }
        } else if (reservasVueloRequestDTO.getPaymentMethod().getType().equals("DEBIT")) {
            total = amount;
            if (reservasVueloRequestDTO.getPaymentMethod().getDues() != 1)
                throw new VuelosException("Se ha ingresado una cantidad de cuotas diferente a 1 ", HttpStatus.BAD_REQUEST);
        }
        Pago pago = new Pago(reservasVueloRequestDTO.getPaymentMethod().getType(), reservasVueloRequestDTO.getPaymentMethod().getNumber(), reservasVueloRequestDTO.getPaymentMethod().getDues());
        repositoryPago.save(pago);
        rsv.setPaymentMethod(pago);
        rsv.setAmount(amount);
        rsv.setInterest(interest);
        rsv.setTotal(total);
    }

    private void person(ReservasVueloRequestDTO reservasVueloRequestDTO, ReservaVuelo rsv) {
        List<Persona> l = new ArrayList<>();
        for (PersonaDTO p : reservasVueloRequestDTO.getFlightReservation().getPeople()) {
            Persona persona = new Persona(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
            l.add(persona);
            repositoryPerson.save(persona);
        }
        rsv.setPeople(l);
        rsv.setSeats(reservasVueloRequestDTO.getFlightReservation().getSeats());
    }

    private void validation(ReservasVueloRequestDTO reservasVueloRequestDTO, Long id) throws VuelosException, PersonasException {
        if (!repositoryFlight.existsVuelo(reservasVueloRequestDTO.getFlightReservation().getFlightNumber()))
            throw new VuelosException("No existe vuelo con ese codigo", HttpStatus.BAD_REQUEST);
        if (!repositoryReservation.existsReservaVueloById(id))
            throw new VuelosException("No Hay reservas con ese codigo", HttpStatus.BAD_REQUEST);
        if (reservasVueloRequestDTO.getFlightReservation().getSeats() != reservasVueloRequestDTO.getFlightReservation().getPeople().size())
            throw new PersonasException("La cantidad de Personas no coincide", HttpStatus.BAD_REQUEST);
    }

}


//    /**
//     * @param rsVuelo contiene los datos ncesarios para la reserva:
//     *                username
//     *                flightReservation que contiene :
//     *                dateFrom
//     *                dateTo
//     *                origin
//     *                destination
//     *                flightNumber
//     *                seats
//     *                seatsType
//     *                people que contiene:
//     *                dni
//     *                name
//     *                lastname
//     *                birthDate
//     *                mail
//     *                paymentMethod:
//     *                type
//     *                number
//     *                dues
//     * @return Comprobante de la reserva que esta compuesta por los datos ingresados anteriormente mas el costo de dicha reserva sumando el monto, intereses y el total
//     * @throws PersonasException  Excepcion causada por si la cantidad de personas no coincide con la cantidad de datos ingresados
//     * @throws FechasException    Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada
//     * @throws VuelosException    Excepcion causada por si en el sistema no se registran vuelos de acuerdo a los parametros ingresados anteriormente.
//     * @throws UbicacionException Excepcion causada por si no existen vuelos en el origen ingresado o en el destino.
//     */

//    public ReservaVueloResponseDTO addReserva(ReservasVueloRequestDTO rsVuelo) throws PersonasException, FechasException, VuelosException, UbicacionException {
//        if (!serviceVuelo.existsDestinationVuelo(rsVuelo.getFlightReservation().getDestination()))
//            throw new UbicacionException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
//        if (!serviceVuelo.existsOriginVuelo(rsVuelo.getFlightReservation().getOrigin()))
//            throw new UbicacionException("El origen elegido no existe", HttpStatus.BAD_REQUEST);
//        if (rsVuelo.getFlightReservation().getSeats() != rsVuelo.getFlightReservation().getPeople().size())
//            throw new PersonasException("La cantidad de Personas no coincide", HttpStatus.BAD_REQUEST);
//        if (!serviceVuelo.existsVuelo(rsVuelo.getFlightReservation().getFlightNumber()))
//            throw new VuelosException("No existe un Vuelo con ese Codigo", HttpStatus.BAD_REQUEST);
//        ReservaVueloResponseDTO res;
//        DisponibilidadVuelosDTO nuevo = new DisponibilidadVuelosDTO(rsVuelo.getFlightReservation().getDateFrom(), rsVuelo.getFlightReservation().getDateTo(), rsVuelo.getFlightReservation().getOrigin(), rsVuelo.getFlightReservation().getDestination());
//        List<VueloDTO> vuelosDisponibles = serviceVuelo.disponibilidadVuelos(nuevo);
//        VueloDTO vuelo = null;
//        for (VueloDTO v : vuelosDisponibles)
//            if (v.getFlightNumber().equals(rsVuelo.getFlightReservation().getFlightNumber())) {
//                vuelo = v;
//                break;
//            }
//        if (vuelo != null) {
//            List<Persona> l = new ArrayList<>();
//            for (PersonaDTO p : rsVuelo.getFlightReservation().getPeople()) {
//                Persona persona = new Persona(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
//                l.add(persona);
//            }
//            double interest = 0;
//            double amount = vuelo.getPricePerPerson() * rsVuelo.getFlightReservation().getSeats();
//            double total = 0;
//            if (rsVuelo.getPaymentMethod().getType().equals("CREDIT")) {
//                if (rsVuelo.getPaymentMethod().getDues() <= 3) {
//                    interest = 5;
//                    total = amount * interest;
//                } else if ((rsVuelo.getPaymentMethod().getDues() >= 3) && ((rsVuelo.getPaymentMethod().getDues() <= 6))) {
//                    interest = 10;
//                    total = amount * interest;
//                }
//            } else if (rsVuelo.getPaymentMethod().getType().equals("DEBIT")) {
//                total = amount;
//                if (rsVuelo.getPaymentMethod().getDues() != 1)
//                    throw new VuelosException("Se ha ingresado una cantidad de cuotas diferente a 1 ", HttpStatus.BAD_REQUEST);
//            }
//            Pago pago = new Pago(rsVuelo.getPaymentMethod().getType(), rsVuelo.getPaymentMethod().getNumber(), rsVuelo.getPaymentMethod().getDues());
//            ReservaVuelo rsv = new ReservaVuelo(rsVuelo.getUserName(), rsVuelo.getFlightReservation().getDateFrom(), rsVuelo.getFlightReservation().getDateTo(), rsVuelo.getFlightReservation().getDestination(), l, pago, amount, interest, total, rsVuelo.getFlightReservation().getFlightNumber(), rsVuelo.getFlightReservation().getSeats(), rsVuelo.getFlightReservation().getSeatType(), rsVuelo.getFlightReservation().getDestination());
//            ReservaVueloDTO rsv2 = new ReservaVueloDTO(rsVuelo.getFlightReservation().getDateFrom(), rsVuelo.getFlightReservation().getDateTo(), rsVuelo.getFlightReservation().getOrigin(), rsVuelo.getFlightReservation().getDestination(), rsVuelo.getFlightReservation().getFlightNumber(), rsVuelo.getFlightReservation().getSeats(), rsVuelo.getFlightReservation().getSeatType(), rsVuelo.getFlightReservation().getPeople());
//            repository.save(rsv);
//            StatusDTO status = new StatusDTO(200, "El proceso termino satisfactoriamente");
//            res = new ReservaVueloResponseDTO(rsVuelo.getUserName(), amount, interest, total, rsv2, status);
//        } else
//            throw new VuelosException("Ese vuelo No esta disponible en las fechas ingresadas", HttpStatus.BAD_REQUEST);
//        return res;
//    }

//    /**
//     * @param codVuelo Recibe el identificador del vuelo que quiere consultar
//     * @return Todos los datos de las reservas que corresponden al vuelo ingresado.
//     */
//    public List<ReservaVueloResponseDTO> getReservasVuelo(String codVuelo) throws VuelosException {
//        if (!serviceVuelo.existsVuelo(codVuelo))
//            throw new VuelosException("No existe ese vuelo en el sistema", HttpStatus.BAD_REQUEST);
//        List<ReservaVuelo> agenda = repository.getReservasVuelo().get(codVuelo);
//        if (agenda == null)
//            throw new VuelosException("No hay reservas para ese vuelo", HttpStatus.BAD_REQUEST);
//
//        ReservaVueloResponseDTO r;
//        List<ReservaVueloResponseDTO> res = new ArrayList<>();
//
//        for (ReservaVuelo h : agenda) {
//            List<PersonaDTO> personaL = new ArrayList<>();
//            for (Persona p : h.getPeople()) {
//                PersonaDTO per = new PersonaDTO(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
//                personaL.add(per);
//            }
//            ReservaVueloDTO rsv = new ReservaVueloDTO(h.getDateFrom(), h.getDateTo(), h.getOrigin(), h.getDestination(), h.getFlightNumber(), h.getSeats(), h.getSeaType(), personaL);
//            r = new ReservaVueloResponseDTO(h.getUserName(), h.getAmount(), h.getInterest(), h.getTotal(), rsv, null);
//            res.add(r);
//        }
//        return res;
//    }

