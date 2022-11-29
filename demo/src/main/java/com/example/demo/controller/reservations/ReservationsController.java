package com.example.demo.controller.reservations;

import com.example.demo.dtos.ReservationStatus;
import com.example.demo.dtos.ReservationSummary;
import com.example.demo.model.reservations.Reservation;
import com.example.demo.services.reservations.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationsController {
    @Autowired
    ReservationService reservationService;

    @CrossOrigin
    @PostMapping("/reservations/{costumerId}")
    public void postReservation(@RequestBody Reservation reservation, @PathVariable int costumerId){
        reservationService.postReservationWithCostumerInfo(reservation, costumerId);
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations(){
        List<Reservation> reservationList = reservationService.getAllReservations();

        return reservationList;
    }

    @CrossOrigin
    @GetMapping("/reservations/{reservationId}")
    public Reservation getReservationByID(@PathVariable int reservationId){
        return reservationService.getById(reservationId);
    }

    @CrossOrigin
    @GetMapping("/reservations/status")
    @ResponseBody
    public List<ReservationStatus> getAllReservationsStatus(){
        List <ReservationStatus> reservationStatuses =reservationService.getAllReservationsStatus();

        return reservationStatuses;
    }

    @CrossOrigin
    @GetMapping("/reservation/{reservationId}/cancel")
    public double getReturnPrice(@PathVariable int reservationId){
        return reservationService.cancelReservation(reservationId);
    }
    @CrossOrigin
    @GetMapping("/reservation/{reservationId}/{roomId}/cancelRoom")
    public double getSingleRoomReturnPrice(@PathVariable int reservationId, @PathVariable int roomId){
        return reservationService.cancelRoomFromReservation(reservationId,roomId);
    }

    @CrossOrigin
    @GetMapping("/reservation/{reservationId}/summary")
    public ReservationSummary getReservationSummary(@PathVariable int reservationId){
        return reservationService.getSummary(reservationId);
    }

    @CrossOrigin
    @DeleteMapping("/reservations/{reservationId}")
    public void deleteReservation(@PathVariable int reservationId) {reservationService.delete(reservationId);}
}
