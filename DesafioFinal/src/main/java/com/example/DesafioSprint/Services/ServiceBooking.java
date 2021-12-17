package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Entities.Hotel;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Exceptions.UbicacionException;
import com.example.DesafioSprint.Entities.Pago;
import com.example.DesafioSprint.Entities.Persona;
import com.example.DesafioSprint.Entities.ReservaHotel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ServiceBooking implements IServiceBooking {

    private IHotelRepository hotelRepository;
    private IServiceHotel hotelService;

    public ServiceBooking(IHotelRepository hotelRepository, IServiceHotel serviceHotel) {
        this.hotelRepository = hotelRepository;
        this.hotelService = serviceHotel;
    }

    /**
     * @param bookingRequestDTO contiene los datos ncesarios para la reserva:
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
    public BookingResponseDTO addReserva(BookingRequestDTO bookingRequestDTO) throws PersonasException, HotelesException, FechasException {

        validateBooking(bookingRequestDTO);

        BookingResponseDTO res = null;
        DisponibilidadHotelDTO hotRsv = new DisponibilidadHotelDTO(bookingRequestDTO.getBooking().getDateFrom(), bookingRequestDTO.getBooking().getDateTo(), bookingRequestDTO.getBooking().getDestination());
        ListHotelesDTO lstHoteles = hotelService.getHotelesDisponibles(hotRsv);
        HotelDTO hotel = null;

        for (HotelDTO h : lstHoteles.getHoteles()) {
            if (h.getHotelCode().equals(bookingRequestDTO.getBooking().getHotelCode())) {
                hotel = h;
                break;
            }
        }

        if (hotel != null) {
            List<Persona> l = new ArrayList<>();
            for (PersonaDTO p : bookingRequestDTO.getBooking().getPeople()) {
                Persona nuevo = new Persona(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
                l.add(nuevo);
            }
            double interest = 0;
            double amount = 0,total = 0;
            double tripDuration = TimeUnit.MILLISECONDS.toDays(Math.abs(bookingRequestDTO.getBooking().getDateFrom().getTime() - bookingRequestDTO.getBooking().getDateTo().getTime()));
            amount = tripDuration * hotel.getPriceByNight() * bookingRequestDTO.getBooking().getPeopleAmount();

            if (bookingRequestDTO.getPaymentMethod().getType().equals("CREDIT")) {
                if (bookingRequestDTO.getPaymentMethod().getDues() <= 3) {
                    interest = 5;
                    total = amount * interest;
                } else if ((bookingRequestDTO.getPaymentMethod().getDues() > 3) && ((bookingRequestDTO.getPaymentMethod().getDues() <= 6))) {
                    interest = 10;
                    total = amount * interest;
                }
            } else if (bookingRequestDTO.getPaymentMethod().getType().equals("DEBIT")) {
                total = amount;
                if (bookingRequestDTO.getPaymentMethod().getDues() != 1)
                    throw new HotelesException("Se ha ingresado una cantidad de cuotas diferente a 1 ", HttpStatus.BAD_REQUEST);
            }
            Pago pago = new Pago(bookingRequestDTO.getPaymentMethod().getType(), bookingRequestDTO.getPaymentMethod().getNumber(), bookingRequestDTO.getPaymentMethod().getDues());
            ReservaHotel rsv = new ReservaHotel(bookingRequestDTO.getUsername(), bookingRequestDTO.getBooking().getDateFrom(), bookingRequestDTO.getBooking().getDateTo(), bookingRequestDTO.getBooking().getDestination(), l, pago, amount, interest, total, bookingRequestDTO.getBooking().getHotelCode(), bookingRequestDTO.getBooking().getPeopleAmount(), bookingRequestDTO.getBooking().getRoomType());
            BookingDTO bookingDTO = rsv.bookingToDTO();
            hotelService.addreserva(rsv);
            StatusDTO status = new StatusDTO(200, "El proceso termino satisfactoriamente");
            res = new BookingResponseDTO(bookingRequestDTO.getUsername(), amount, interest, total, rsv2, status);
        } else
            throw new HotelesException("Ese hotel No esta disponible en las fechas ingresadas", HttpStatus.BAD_REQUEST);
        return res;
    }



    private void validateBooking(BookingRequestDTO bookingRequestDTO) throws PersonasException, HotelesException, FechasException{
        if (!hotelService.existsDestination(bookingRequestDTO.getBooking().getDestination()))
            throw new HotelesException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
        if (!hotelService.existsHoteles(bookingRequestDTO.getBooking().getHotelCode()))
            throw new HotelesException("No existe un Hotel con ese Codigo", HttpStatus.BAD_REQUEST);
        if (bookingRequestDTO.getBooking().getPeople().size() != bookingRequestDTO.getBooking().getPeopleAmount())
            throw new PersonasException("La cantidad de Personas No coincide", HttpStatus.BAD_REQUEST);
        if (bookingRequestDTO.getBooking().getRoomType().equals("DOUBLE") && bookingRequestDTO.getBooking().getPeopleAmount() > 2)
            throw new PersonasException("La Habitacion es para 2 personas", HttpStatus.BAD_REQUEST);
        if (bookingRequestDTO.getBooking().getRoomType().equals("TRIPLE") && bookingRequestDTO.getBooking().getPeopleAmount() > 3)
            throw new PersonasException("La Habitacion es para 3 personas", HttpStatus.BAD_REQUEST);
        if (bookingRequestDTO.getBooking().getRoomType().equals("SINGLE") && bookingRequestDTO.getBooking().getPeopleAmount() > 1)
            throw new PersonasException("La Habitacion es para 1 persona", HttpStatus.BAD_REQUEST);
    }

    private void getPayment(BookingRequestDTO bookingRequestDTO, HotelDTO hotel) throws HotelesException{
        double interest = 0;
        double amount = 0,total = 0;
        double tripDuration = TimeUnit.MILLISECONDS.toDays(Math.abs(bookingRequestDTO.getBooking().getDateFrom().getTime() - bookingRequestDTO.getBooking().getDateTo().getTime()));
        amount = tripDuration * hotel.getPriceByNight() * bookingRequestDTO.getBooking().getPeopleAmount();

        if (bookingRequestDTO.getPaymentMethod().getType().equals("CREDIT")) {
            if (bookingRequestDTO.getPaymentMethod().getDues() <= 3) {
                interest = 5;
                total = amount * interest;
            } else if ((bookingRequestDTO.getPaymentMethod().getDues() > 3) && ((bookingRequestDTO.getPaymentMethod().getDues() <= 6))) {
                interest = 10;
                total = amount * interest;
            }
        } else if (bookingRequestDTO.getPaymentMethod().getType().equals("DEBIT")) {
            total = amount;
            if (bookingRequestDTO.getPaymentMethod().getDues() != 1)
                throw new HotelesException("Se ha ingresado una cantidad de cuotas diferente a 1 ", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @param codHotel Recibe el identificador del vuelo que quiere consultar
     * @return Todos los datos de las reservas que corresponden al vuelo ingresado.
     */

    public List<BookingResponseDTO> getReservasHotel(String codHotel) throws HotelesException {
        if (!hotelService.existsHoteles(codHotel))
            throw new HotelesException("No existe ese hotel en el sistema", HttpStatus.BAD_REQUEST);
        BookingResponseDTO r;
        List<BookingResponseDTO> res = new ArrayList<>();
        List<ReservaHotel> agenda = hotelRepository.getBookings().get(codHotel);
        if (agenda == null)
            throw new HotelesException("No hay reservas para ese hotel", HttpStatus.BAD_REQUEST);

        for (ReservaHotel h : agenda) {
            List<PersonaDTO> personaL = new ArrayList<>();
            for (Persona p : h.getPeople()) {
                PersonaDTO per = new PersonaDTO(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
                personaL.add(per);
            }
            BookingDTO rsv = new BookingDTO(h.getDateFrom(), h.getDateTo(), h.getDestination(), h.getHotelCode(), h.getPeopleAmount(), h.getRoomType(), personaL);
            r = new BookingResponseDTO(h.getUserName(), h.getAmount(), h.getInterest(), h.getTotal(), rsv, null);
            res.add(r);
        }
        return res;
    }

    @Override
    public BookingResponseDTO deleteBooking(Long id) {
         Hotel hotel = hotelRepository.findById(id).get();
         hotelRepository.delete(hotel);
         return new BookingResponseDTO("Hotel dado de baja correctamente");
    }

    @Override
    public BookingResponseDTO updateBooking(HotelDTO hotelDTO) {
        return null;
    }
}