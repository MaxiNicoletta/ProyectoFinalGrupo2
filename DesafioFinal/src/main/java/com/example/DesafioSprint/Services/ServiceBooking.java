package com.example.DesafioSprint.Services;

import com.example.DesafioSprint.DTOs.*;
import com.example.DesafioSprint.Entities.*;
import com.example.DesafioSprint.Exceptions.FechasException;
import com.example.DesafioSprint.Exceptions.HotelesException;
import com.example.DesafioSprint.Exceptions.PersonasException;
import com.example.DesafioSprint.Repository.IBookingRepository;
import com.example.DesafioSprint.Repository.IHotelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ServiceBooking implements IServiceBooking {

    private IBookingRepository bookingRepository;
    private IHotelRepository hotelRepository;
    private IServiceHotel hotelService;

    public ServiceBooking(IBookingRepository bookingRepository, IHotelRepository hotelRepository, IServiceHotel hotelService) {
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
    }

    public BookingResponseDTO addBooking(BookingRequestDTO bookingRequestDTO) throws FechasException, HotelesException, PersonasException {
        validateBooking(bookingRequestDTO);
        HotelDTO hotel = hotels(bookingRequestDTO);
        if (hotel != null) {
            getPersonAndPaymentReservation(bookingRequestDTO, hotel);
            return new BookingResponseDTO("Reserva de hotel dada de alta correctamente");
        } else
            throw new HotelesException("Ese hotel No esta disponible en las fechas ingresadas", HttpStatus.BAD_REQUEST);
    }

    @Override
    public BookingResponseDTO deleteBooking(Long id) {
        Booking booking = bookingRepository.getById(id);
        bookingRepository.delete(booking);
        return new BookingResponseDTO("Reserva dada de baja correctamente");
    }

    @Override
    public BookingResponseDTO updateBooking(Long id, BookingRequestDTO bookingRequestDTO) throws HotelesException, FechasException, PersonasException {
        validateBooking(bookingRequestDTO);
        Booking booking = bookingRepository.getById(id);
        booking.setUserName(bookingRequestDTO.getUsername());
        if (booking.getPeopleAmount()!= bookingRequestDTO.getBooking().getPeopleAmount())
            person(bookingRequestDTO,booking);
        else
            booking.setPeopleAmount(bookingRequestDTO.getBooking().getPeopleAmount());

        if (bookingRequestDTO.getPaymentMethod()!= booking.getPaymentMethod().paymentToDTO())
            getPayment(bookingRequestDTO,booking);

        bookingRepository.save(booking);
        return new BookingResponseDTO("Reserva modificada con exito");
    }

    @Override
    public List<BookingDTO> getBookings() throws HotelesException {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingDTO> bookingsDTO = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingDTO bookingDTO = booking.bookingToDTO();
            bookingsDTO.add(bookingDTO);
        }
        return bookingsDTO;
    }


    private HotelDTO hotels(BookingRequestDTO bookingRequestDTO) throws FechasException, HotelesException {
        DisponibilidadHotelDTO disponibilidadHotelDTO = new DisponibilidadHotelDTO(bookingRequestDTO.getBooking().getDateFrom(), bookingRequestDTO.getBooking().getDateTo(), bookingRequestDTO.getBooking().getDestination());
        ListHotelesDTO lstHoteles = hotelService.getHotelesDisponibles(disponibilidadHotelDTO);
        HotelDTO hotel = null;
        for (HotelDTO h : lstHoteles.getHoteles()) {
            if (h.getHotelCode().equals(bookingRequestDTO.getBooking().getHotelCode())) {
                hotel = h;
                break;
            }
        }
        return hotel;
    }

    private void getPersonAndPaymentReservation(BookingRequestDTO bookingRequestDTO, HotelDTO hotel) throws HotelesException {
        List<Persona> people = new ArrayList<>();
        for (PersonaDTO personaDTO : bookingRequestDTO.getBooking().getPeople()) {
            Persona person = new Persona(personaDTO.getDni(), personaDTO.getName(), personaDTO.getLastname(), personaDTO.getBirthDate(), personaDTO.getMail());
            people.add(person);
        }
        double interest = 0;
        double amount = 0, total = 0;
        double tripDuration = TimeUnit.MILLISECONDS.toDays(Math.abs(bookingRequestDTO.getBooking().getDateFrom().getTime() - bookingRequestDTO.getBooking().getDateTo().getTime()));
        amount = tripDuration * hotel.getRoomPrice() * bookingRequestDTO.getBooking().getPeopleAmount();

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
        }// SetPayment
        Pago pago = new Pago(bookingRequestDTO.getPaymentMethod().getType(), bookingRequestDTO.getPaymentMethod().getNumber(), bookingRequestDTO.getPaymentMethod().getDues());
        Booking booking = new Booking(bookingRequestDTO.getUsername(), bookingRequestDTO.getBooking().getDateFrom(), bookingRequestDTO.getBooking().getDateTo(), bookingRequestDTO.getBooking().getDestination(), people, pago, amount, interest, total, bookingRequestDTO.getBooking().getHotelCode(), bookingRequestDTO.getBooking().getPeopleAmount(), bookingRequestDTO.getBooking().getRoomType());
        bookingRepository.save(booking);
    }

    private void getPayment(BookingRequestDTO bookingRequestDTO, Booking booking) throws HotelesException {
        double interest = 0;
        double amount = 0, total = 0;
        double tripDuration = TimeUnit.MILLISECONDS.toDays(Math.abs(bookingRequestDTO.getBooking().getDateFrom().getTime() - bookingRequestDTO.getBooking().getDateTo().getTime()));
        Hotel hotel = hotelRepository.findHoteltByCod(bookingRequestDTO.getBooking().getHotelCode());
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
        }// SetPayment
        Pago pago = new Pago(bookingRequestDTO.getPaymentMethod().getType(), bookingRequestDTO.getPaymentMethod().getNumber(), bookingRequestDTO.getPaymentMethod().getDues());
        booking.setPaymentMethod(pago);
        booking.setAmount(amount);
        booking.setInterest(interest);
        booking.setTotal(total);
}

    private void validateBooking(BookingRequestDTO bookingRequestDTO) throws PersonasException, HotelesException, FechasException {
        if (!hotelRepository.existsDestinationHotel(bookingRequestDTO.getBooking().getDestination())) {
            throw new HotelesException("El destino elegido no existe", HttpStatus.BAD_REQUEST);
        }
        if (!hotelRepository.existsHotel(bookingRequestDTO.getBooking().getHotelCode())) {
            throw new HotelesException("No existe un Hotel con ese Codigo", HttpStatus.BAD_REQUEST);
        }
        if (bookingRequestDTO.getBooking().getPeople().size() != bookingRequestDTO.getBooking().getPeopleAmount())
            throw new PersonasException("La cantidad de Personas No coincide", HttpStatus.BAD_REQUEST);
        if (bookingRequestDTO.getBooking().getRoomType().equals("DOUBLE") && bookingRequestDTO.getBooking().getPeopleAmount() > 2)
            throw new PersonasException("La Habitacion es para 2 personas", HttpStatus.BAD_REQUEST);
        if (bookingRequestDTO.getBooking().getRoomType().equals("TRIPLE") && bookingRequestDTO.getBooking().getPeopleAmount() > 3)
            throw new PersonasException("La Habitacion es para 3 personas", HttpStatus.BAD_REQUEST);
        if (bookingRequestDTO.getBooking().getRoomType().equals("SINGLE") && bookingRequestDTO.getBooking().getPeopleAmount() > 1)
            throw new PersonasException("La Habitacion es para 1 persona", HttpStatus.BAD_REQUEST);
    }
    private void person (BookingRequestDTO bookingRequestDTO, Booking booking){
        List<Persona> l = new ArrayList<>();
        for (PersonaDTO p : bookingRequestDTO.getBooking().getPeople()) {
            Persona persona = new Persona(p.getDni(), p.getName(), p.getLastname(), p.getBirthDate(), p.getMail());
            l.add(persona);
        }
        booking.setPeople(l);
        booking.setPeopleAmount(bookingRequestDTO.getBooking().getPeopleAmount());
    }

}