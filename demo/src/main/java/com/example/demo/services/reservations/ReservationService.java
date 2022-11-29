package com.example.demo.services.reservations;

import com.example.demo.dtos.ReservationStatus;
import com.example.demo.dtos.ReservationSummary;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.reservations.Reservation;
import com.example.demo.model.rooms.*;
import com.example.demo.repositories.persons.CustomerCRUDRepository;
import com.example.demo.repositories.reservations.ReservationCRUDRepository;
import com.example.demo.repositories.rooms.DoubleRoomCRUDRepository;
import com.example.demo.repositories.rooms.FamilyRoomCRUDRepository;
import com.example.demo.repositories.rooms.SingleRoomCRUDRepository;
import com.example.demo.repositories.rooms.SuiteCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.stream.Collectors;

@Service
public class ReservationService {
    @Autowired
    ReservationCRUDRepository reservationCRUDRepository;
    @Autowired
    SingleRoomCRUDRepository singleRoomCRUDRepository;
    @Autowired
    DoubleRoomCRUDRepository doubleRoomCRUDRepository;
    @Autowired
    FamilyRoomCRUDRepository familyRoomCRUDRepository;
    @Autowired
    SuiteCRUDRepository suiteCRUDRepository;
    @Autowired
    CustomerCRUDRepository customerCRUDRepository;


    public void postReservationWithCostumerInfo(Reservation reservation, int costumerId){
        Reservation newReservation = new Reservation();
        Customer costumer = customerCRUDRepository.findById(costumerId).get();
        newReservation.setCustomerInfo(costumer.geteMail());
        newReservation.setOccupants(costumer.getNumberOfCostumers());
        newReservation.setCheckIn(costumer.getCheckIn());
        newReservation.setCheckOut(costumer.getCheckOut());
        newReservation.setReservationsDate(LocalDate.now());
        newReservation.setCustomer(costumer);
        reservationCRUDRepository.save(newReservation);



    }
    public List<Reservation> getAllReservations(){
        return ((List<Reservation>) reservationCRUDRepository.findAll());
    }

    public ReservationSummary getSummary(int reservationId) {
       Reservation reservation = reservationCRUDRepository.findById(reservationId).get();
       ReservationSummary reservationSummary = new ReservationSummary(reservation.getReservationId(),reservation.getReservationsDate(),reservation.getCheckIn(),
               reservation.getCheckOut(),reservation.getCustomerInfo(),reservation.getSingleRooms(),reservation.getDoubleRooms(),reservation.getFamilyRooms(),
               reservation.getSuites(),reservation.getFinalPrice());
       return  reservationSummary;
   }


    public Reservation getById(int reservationId){
        Reservation reservation = reservationCRUDRepository.findById(reservationId).get();
        return reservation;
    }

    public List<ReservationStatus> getAllReservationsStatus(){
        return ((List<Reservation>) reservationCRUDRepository
                .findAll())
                // Is a Sequence of objects in this case all the Objects from the List<Reservation>
                .stream()
                // .map is a method from stream used to apply a given function to the elements of the stream
                .map(this::convertToReservationStatus)
                //Used to return the result of the operations done in the Stream
                .collect(Collectors.toList());
    }
    public static long daysBetweenTwoDates (LocalDate firstDate, LocalDate secondDate){
        return Math.abs(ChronoUnit.DAYS.between(firstDate, secondDate));
    }

    //The High season price adjustment is present and applied
    private ReservationStatus convertToReservationStatus(Reservation reservation){
        ReservationStatus reservationStatus = new ReservationStatus();
        
        LocalDate beginHighSeason = LocalDate.of(Year.now().getValue(),6,1);
        LocalDate endHighSeason = LocalDate.of(Year.now().getValue(),12,31);
        Period timeOfStay = Period.between(reservation.getCheckIn(), reservation.getCheckOut());
        long daysOfStay = daysBetweenTwoDates(beginHighSeason,endHighSeason);
        
        if (reservation.getCheckIn().isAfter(beginHighSeason) && reservation.getCheckOut().isBefore(endHighSeason)){
            reservationStatus.setFinalPrice(reservation.getFinalPrice() *1.1);
        }else if (reservation.getCheckIn().isBefore(endHighSeason)){
            Period period;
            int timeDif;
            double alterPrice;
            if (reservation.getCheckIn().isBefore(endHighSeason) && !reservation.getCheckOut().isAfter(endHighSeason)){
                period = Period.between(beginHighSeason, reservation.getCheckIn());
                timeDif = period.getDays()*(-1);
                if (timeDif > daysOfStay){
                    alterPrice = ((double) timeDif/daysOfStay)*10/100 +1;
                }else {
                    alterPrice = ((double) (daysOfStay - timeDif)/ daysOfStay) *10/100+1;
                }
            }else {
                period = Period.between(endHighSeason,reservation.getCheckOut());
                timeDif = period.getDays();
                if (timeDif> daysOfStay){
                    alterPrice = ((double) timeDif/daysOfStay)*100/100+1;
                }else {
                    alterPrice = ((double) (daysOfStay - timeDif)/daysOfStay)*10/100+1;
                }
            }
            reservationStatus.setFinalPrice(alterPrice* reservation.getFinalPrice());
        }else
            reservationStatus.setFinalPrice(reservation.getFinalPrice());

        reservationStatus.setComplete(reservation.isComplete());
        reservationStatus.setCheckIn(reservation.getCheckIn());
        reservationStatus.setCheckOut(reservation.getCheckOut());
        return reservationStatus;
    }
    public void updateReservation (Reservation reservation,double roomPrice, int numberOfResidents){
        long durationOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
        roomPrice = roomPrice*durationOfStay;
        reservation.setFinalPrice(reservation.getFinalPrice()-roomPrice);
        reservation.setMaxNumberOfResidents(reservation.getMaxNumberOfResidents()-numberOfResidents);
        if (reservation.getMaxNumberOfResidents()>=reservation.getOccupants()){
            reservation.setComplete(true);
        }else
            reservation.setComplete(false);
    }
    public double calculateReturnPrice(double returnPrice, LocalDate firstDate, LocalDate secondDate, LocalDate thirdDate){
        long duration = daysBetweenTwoDates(firstDate, secondDate);
        long duration2 = daysBetweenTwoDates(firstDate,thirdDate);
        long duration3 = daysBetweenTwoDates(secondDate, thirdDate);
        if (duration2<=7 && duration3 > 21 ){
            return returnPrice;
        }else if ( duration2 <=7 && duration3 <21 && duration3 > 14){
            return returnPrice*0.5;
        }else if (duration2 <=7 && duration3 <14 && duration3 >7){
            return returnPrice*0.3;
        }else if (duration2 <=7 && duration3 <7){
            return returnPrice* 0;
        }else if (duration > 21){
            return returnPrice;
        }else if (duration <=21 && duration >14){
            return returnPrice*0.5;
        }else if (duration <=14 && duration >= 7){
            return returnPrice*0.3;
        }else if (duration <7){
            return returnPrice*0;
        }
        return returnPrice;
    }
    public double cancelReservation(int reservationId) {
        Reservation reservationToBeCancelled = reservationCRUDRepository.findById(reservationId).get();
        return calculateReturnPrice(reservationToBeCancelled.getFinalPrice(), LocalDate.
        now(), reservationToBeCancelled.getCheckIn(), reservationToBeCancelled.getReservationsDate());

    }
    public double cancelRoomFromReservation(int reservationId, int roomId) {
        Reservation reservation = reservationCRUDRepository.findById(reservationId).get();
        double priceToBeReturn = 0;
        long timeOfStay;
        if (reservation.getSingleRooms().size() >= 1) {
            for (SingleRoom singleRoom : reservation.getSingleRooms()) {
                if (singleRoom.getRoomId() == roomId) {
                    SingleRoom singleRoom1 = singleRoomCRUDRepository.findById(roomId).get();
                    timeOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
                    priceToBeReturn = calculateReturnPrice(singleRoom1.getPrice(), LocalDate.now(),
                            reservation.getCheckIn(), reservation.getReservationsDate())* timeOfStay;
                    updateReservation(reservation,singleRoom.getPrice(),singleRoom.getMaxNumberOfResidents());

                    Set<SingleRoom> singleRooms = new HashSet<>();
                    singleRooms.remove(singleRoom1);
                    reservation.removeSingleRoom(singleRoom1);
                    singleRoomCRUDRepository.save(singleRoom1);
                }
            }
        }
        if (reservation.getDoubleRooms().size() >= 1) {
            for (DoubleRoom doubleRoom : reservation.getDoubleRooms()) {
                if (doubleRoom.getRoomId() == roomId) {
                    DoubleRoom doubleRoom1 = doubleRoomCRUDRepository.findById(roomId).get();
                    timeOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
                    priceToBeReturn = calculateReturnPrice(doubleRoom1.getPrice(), LocalDate.now(),
                            reservation.getCheckIn(), reservation.getReservationsDate())* timeOfStay;
                    updateReservation(reservation,doubleRoom.getPrice(),doubleRoom.getMaxNumberOfResidents());

                    Set<DoubleRoom> doubleRooms = new HashSet<>();
                    doubleRooms.remove(doubleRoom1);
                    reservation.removeDoubleRoom(doubleRoom1);
                    doubleRoomCRUDRepository.save(doubleRoom1);
                }
            }
        }
        if (reservation.getFamilyRooms().size() >= 1) {
            for (FamilyRoom familyRoom : reservation.getFamilyRooms()) {
                if (familyRoom.getRoomId() == roomId) {
                    FamilyRoom familyRoom1 = familyRoomCRUDRepository.findById(roomId).get();
                    timeOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
                    priceToBeReturn = calculateReturnPrice(familyRoom1.getPrice(), LocalDate.now(),
                            reservation.getCheckIn(), reservation.getReservationsDate())* timeOfStay;
                    updateReservation(reservation,familyRoom.getPrice(),familyRoom.getMaxNumberOfResidents());

                    Set<FamilyRoom> familyRooms = new HashSet<>();
                    familyRooms.remove(familyRoom1);
                    reservation.removeFamilyRoom(familyRoom1);
                    familyRoomCRUDRepository.save(familyRoom1);
                }
            }
        }
        if (reservation.getSuites().size() >= 1) {
            for (Suite suite : reservation.getSuites()) {
                if (suite.getRoomId() == roomId) {
                    Suite suite1 = suiteCRUDRepository.findById(roomId).get();
                    timeOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
                    priceToBeReturn = calculateReturnPrice(suite1.getPrice(), LocalDate.now(),
                            reservation.getCheckIn(), reservation.getReservationsDate())* timeOfStay;
                    updateReservation(reservation,suite.getPrice(),suite.getMaxNumberOfResidents());

                    Set<Suite> suites = new HashSet<>();
                    suites.remove(suite1);
                    reservation.removeSuite(suite1);
                    suiteCRUDRepository.save(suite1);
                }
            }
        }
        return priceToBeReturn;
    }
    public void delete (int reservationId){
        reservationCRUDRepository.deleteById(reservationId);
    }
}
