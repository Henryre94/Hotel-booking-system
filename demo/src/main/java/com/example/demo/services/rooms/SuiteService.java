package com.example.demo.services.rooms;

import com.example.demo.dtos.RoomStatus;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.reservations.Reservation;
import com.example.demo.model.rooms.Suite;
import com.example.demo.repositories.persons.CustomerCRUDRepository;
import com.example.demo.repositories.reservations.ReservationCRUDRepository;
import com.example.demo.repositories.rooms.SuiteCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.services.reservations.ReservationService.daysBetweenTwoDates;

@Service
public class SuiteService {
    @Autowired
    SuiteCRUDRepository suiteCRUDRepository;

    @Autowired
    ReservationCRUDRepository reservationCRUDRepository;

    @Autowired
    CustomerCRUDRepository customerCRUDRepository;

    public void post(Suite suite){
        suiteCRUDRepository.save(suite);
    }

    public List<Suite> getAllSuites(){
        return ((List<Suite>) suiteCRUDRepository.findAll());

    }

    public Suite get(int id){
        Suite suite = suiteCRUDRepository.findById(id).get();
        return suite;
    }

    public List<Suite> getAllAvailableRooms (int id){
        Customer newCostumer = customerCRUDRepository.findById(id).get();
        for(Reservation reservationList : reservationCRUDRepository.findAll()){
            if (!newCostumer.getCheckIn().isAfter(reservationList.getCheckIn()) && !newCostumer.getCheckOut().isBefore(reservationList.getCheckOut())) {
                for (Suite suite : reservationList.getSuites()) {
                    suite.setAvailable(false);
                    suiteCRUDRepository.save(suite);
                }
            }
        }
        return ((List<Suite>) suiteCRUDRepository.findAvailable());
    }

    public List<RoomStatus> getAllSuitesStatus(){
        return ((List<Suite>) suiteCRUDRepository
                .findAll())
                // Is a Sequence of objects in this case all the Objects from the List<FamilyRoom>
                .stream()
                // .map is a method from stream used to apply a given function to the elements of the stream
                .map(this::convertToRoomStatus)
                //Used to return the result of the operations done in the Stream
                .collect(Collectors.toList());
    }
    // This method is used to save the information needed in the DTO. The method is applied to every Object in the stream
    private RoomStatus convertToRoomStatus(Suite suite){
        RoomStatus roomStatus = new RoomStatus();
        roomStatus.setAvailable(suite.isAvailable());
        roomStatus.setRoomNumber(suite.getRoomNumber());
        roomStatus.setPrice(suite.getPrice());
        return roomStatus;
    }

    public void bindSuiteToReservation(int reservationId, int roomId){
        Reservation reservation = reservationCRUDRepository.findById(reservationId).get();
        Suite newSuite = suiteCRUDRepository.findById(roomId).get();

        long durationOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
        double suitePrice =newSuite.getPrice()*durationOfStay;

        reservation.setFinalPrice(reservation.getFinalPrice() + suitePrice);
        reservation.setMaxNumberOfResidents(reservation.getMaxNumberOfResidents()+newSuite.getMaxNumberOfResidents());
        if (reservation.getMaxNumberOfResidents()>=reservation.getOccupants()){
            reservation.setComplete(true);
        }else
            reservation.setComplete(false);

        Set<Suite> suites = new HashSet<>();
        suites.add(newSuite);
        reservation.newSuite(newSuite);
       suiteCRUDRepository.save(newSuite);
    }

    public void put (Suite suite) {
        suiteCRUDRepository.save(suite);
    }
    public void delete (int id){
        suiteCRUDRepository.deleteById(id);
    }
}
