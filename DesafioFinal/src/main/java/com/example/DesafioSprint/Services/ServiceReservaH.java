package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Entities.Pago;
import com.example.DesafioSprint.Entities.Persona;
import com.example.DesafioSprint.Entities.ReservaHotel;
import com.example.DesafioSprint.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ServiceReservaH implements IServiceReservaH {

    @Autowired
    private FlightRepository repository = new FlightRepository();

    @Autowired
    private ServiceHotel sr = new ServiceHotel();

    /**
     * @param rvHot contiene los datos ncesarios para la reserva:
     *              username
     *              booking que contiene :
     *              dateFrom
     *              dateTo
     *              destination
     *              hotelCode
     *              peopleAmount
     *              roomType
     *              people que contiene:
     *              dni
     *              name
     *              lastname
     *              birthDate
     *              mail
     *              paymentMethod:
     *              type
     *              number
     *              dues
     * @return Comprobante de la reserva que esta compuesta por los datos ingresados anteriormente mas el costo de dicha reserva sumando el monto, intereses y el total
     * @throws PersonasException  Excepcion cauda por si la cantidad de personas no coincide con la cantidad de datos ingresados
     * @throws FechasException    Excepcion causada por si la fecha de Salida es mayor a la fecha de entrada
     * @throws UbicacionException Excepcion causada por si no existen hoteles en el origen ingresado o en el destino.
     */
    public ReservasHotelDTOResponse addReserva(ReservaHotelRequestDTO rvHot) throws PersonasException, HotelesException, FechasException {
        if (!sr.existsDestination(rvHot.getBooking().getDestination()))
            throw new HotelesException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
        if (!sr.existsHoteles(rvHot.getBooking().getHotelCode()))
            throw new HotelesException("No existe un Hotel con ese Codigo", HttpStatus.BAD_REQUEST);
        if (rvHot.getBooking().getPeople().size() != rvHot.getBooking().getPeopleAmount())
            throw new PersonasException("La cantidad de Personas No coincide", HttpStatus.BAD_REQUEST);
        if (rvHot.getBooking().getRoomType().equals("DOUBLE") && rvHot.getBooking().getPeopleAmount() > 2)
            throw new PersonasException("La Habitacion es para 2 personas", HttpStatus.BAD_REQUEST);
        if (rvHot.getBooking().getRoomType().equals("TRIPLE") && rvHot.getBooking().getPeopleAmount() > 3)
            throw new PersonasException("La Habitacion es para 3 personas", HttpStatus.BAD_REQUEST);
        if (rvHot.getBooking().getRoomType().equals("SINGLE") && rvHot.getBooking().getPeopleAmount() > 1)
            throw new PersonasException("La Habitacion es para 1 persona", HttpStatus.BAD_REQUEST);
        ReservasHotelDTOResponse res = null;
        DisponibilidadHotelDTO hotRsv = new DisponibilidadHotelDTO(rvHot.getBooking().getDateFrom(), rvHot.getBooking().getDateTo(), rvHot.getBooking().getDestination());
        ListHotelesDTO lstHoteles = sr.getHotelesDisponibles(hotRsv);
        HotelDTO hotel = null;

        for (HotelDTO h : lstHoteles.getHoteles()) {
            if (h.getHotelCode().equals(rvHot.getBooking().getHotelCode())) {
                hotel = h;
                break;
            }
        }

        if (hotel != null) {
            List<Persona> l = new ArrayList<>();
            for (PersonaDTO p : rvHot.getBooking().getPeople()) {
                Persona nuevo = new Persona(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
                l.add(nuevo);
            }
            double interest = 0;
            double amount = 0,total = 0;
            double tripDuration = TimeUnit.MILLISECONDS.toDays(Math.abs(rvHot.getBooking().getDateFrom().getTime() - rvHot.getBooking().getDateTo().getTime()));
            amount = tripDuration * hotel.getPriceByNight() * rvHot.getBooking().getPeopleAmount();

            if (rvHot.getPaymentMethod().getType().equals("CREDIT")) {
                if (rvHot.getPaymentMethod().getDues() <= 3) {
                    interest = 5;
                    total = amount * interest;
                } else if ((rvHot.getPaymentMethod().getDues() > 3) && ((rvHot.getPaymentMethod().getDues() <= 6))) {
                    interest = 10;
                    total = amount * interest;
                }
            } else if (rvHot.getPaymentMethod().getType().equals("DEBIT")) {
                total = amount;
                if (rvHot.getPaymentMethod().getDues() != 1)
                    throw new HotelesException("Se ha ingresado una cantidad de cuotas diferente a 1 ", HttpStatus.BAD_REQUEST);
            }
            Pago pago = new Pago(rvHot.getPaymentMethod().getType(), rvHot.getPaymentMethod().getNumber(), rvHot.getPaymentMethod().getDues());
            ReservaHotel rsv = new ReservaHotel(rvHot.getUsername(), rvHot.getBooking().getDateFrom(), rvHot.getBooking().getDateTo(), rvHot.getBooking().getDestination(), l, pago, amount, interest, total, rvHot.getBooking().getHotelCode(), rvHot.getBooking().getPeopleAmount(), rvHot.getBooking().getRoomType());
            ReservaDTO rsv2 = new ReservaDTO(rvHot.getBooking().getDateFrom(), rvHot.getBooking().getDateTo(), rvHot.getBooking().getDestination(), rvHot.getBooking().getHotelCode(), rvHot.getBooking().getPeopleAmount(), rvHot.getBooking().getRoomType(), rvHot.getBooking().getPeople());
            repository.addReservaHotel(rsv);
            StatusDTO status = new StatusDTO(200, "El proceso termino satisfactoriamente");
            res = new ReservasHotelDTOResponse(rvHot.getUsername(), amount, interest, total, rsv2, status);
        } else
            throw new HotelesException("Ese hotel No esta disponible en las fechas ingresadas", HttpStatus.BAD_REQUEST);
        return res;
    }

    /**
     * @param codHotel Recibe el identificador del vuelo que quiere consultar
     * @return Todos los datos de las reservas que corresponden al vuelo ingresado.
     */

    public List<ReservasHotelDTOResponse> getReservasHotel(String codHotel) throws HotelesException {
        if (!sr.existsHoteles(codHotel))
            throw new HotelesException("No existe ese hotel en el sistema", HttpStatus.BAD_REQUEST);
        ReservasHotelDTOResponse r;
        List<ReservasHotelDTOResponse> res = new ArrayList<>();
        List<ReservaHotel> agenda = repository.getReservasHot().get(codHotel);
        if (agenda == null)
            throw new HotelesException("No hay reservas para ese hotel", HttpStatus.BAD_REQUEST);

        for (ReservaHotel h : agenda) {
            List<PersonaDTO> personaL = new ArrayList<>();
            for (Persona p : h.getPeople()) {
                PersonaDTO per = new PersonaDTO(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
                personaL.add(per);
            }
            ReservaDTO rsv = new ReservaDTO(h.getDateFrom(), h.getDateTo(), h.getDestination(), h.getHotelCode(), h.getPeopleAmount(), h.getRoomType(), personaL);
            r = new ReservasHotelDTOResponse(h.getUserName(), h.getAmount(), h.getInterest(), h.getTotal(), rsv, null);
            res.add(r);
        }
        return res;
    }
}
