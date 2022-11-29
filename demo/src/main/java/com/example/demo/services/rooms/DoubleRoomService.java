package com.example.demo.services.rooms;

import com.example.demo.dtos.RoomStatus;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.reservations.Reservation;
import com.example.demo.model.rooms.DoubleRoom;
import com.example.demo.model.rooms.Room;
import com.example.demo.model.rooms.SingleRoom;
import com.example.demo.repositories.persons.CustomerCRUDRepository;
import com.example.demo.repositories.reservations.ReservationCRUDRepository;
import com.example.demo.repositories.rooms.DoubleRoomCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import static com.example.demo.services.reservations.ReservationService.daysBetweenTwoDates;


@Service
public class DoubleRoomService {
    @Autowired
    DoubleRoomCRUDRepository doubleRoomCRUDRepository;

    @Autowired
    ReservationCRUDRepository reservationCRUDRepository;

    @Autowired
    CustomerCRUDRepository customerCRUDRepository;

    public void post(DoubleRoom doubleRoom){
        doubleRoomCRUDRepository.save(doubleRoom);
    }

    public List<DoubleRoom> getAllDoubleRooms(){
        return ((List<DoubleRoom>) doubleRoomCRUDRepository.findAll());

    }

    public DoubleRoom get(int id){
        DoubleRoom doubleRoom = doubleRoomCRUDRepository.findById(id).get();
        return doubleRoom;
    }

    public List<DoubleRoom> getAllAvailableRooms (int id){
        Customer newCostumer = customerCRUDRepository.findById(id).get();
        for(Reservation reservationList : reservationCRUDRepository.findAll()){
            if (!newCostumer.getCheckIn().isAfter(reservationList.getCheckIn()) && !newCostumer.getCheckOut().isBefore(reservationList.getCheckOut())) {
                for (DoubleRoom doubleRoom : reservationList.getDoubleRooms()) {
                    doubleRoom.setAvailable(false);
                    doubleRoomCRUDRepository.save(doubleRoom);
                }
            }
        }
        return ((List<DoubleRoom>) doubleRoomCRUDRepository.findAvailable());
    }

    public List<RoomStatus> getDoubleRoomsStatus(){
        return ((List<DoubleRoom>) doubleRoomCRUDRepository
                .findAll())
                // Is a Sequence of objects in this case all the Objects from the List<DoubleRoom>
                .stream()
                // .map is a method from stream used to apply a given function to the elements of the stream
                .map(this::convertToRoomStatus)
                //Used to return the result of the operations done in the Stream
                .collect(Collectors.toList());
    }
    // This method is used to save the information needed in the DTO. The method is applied to every Object in the stream
    private RoomStatus convertToRoomStatus(DoubleRoom doubleRoom){
        RoomStatus roomStatus = new RoomStatus();
        roomStatus.setAvailable(doubleRoom.isAvailable());
        roomStatus.setRoomNumber(doubleRoom.getRoomNumber());
        roomStatus.setPrice(doubleRoom .getPrice());
        return roomStatus;
    }

    public void bindDoubleRoomToReservation(int reservationId, int roomId){
        Reservation reservation = reservationCRUDRepository.findById(reservationId).get();
        DoubleRoom newDoubleRoom = doubleRoomCRUDRepository.findById(roomId).get();

        long durationOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
        double doubleRoomPrice =newDoubleRoom.getPrice()*durationOfStay;

        reservation.setFinalPrice(reservation.getFinalPrice() + doubleRoomPrice);
        reservation.setMaxNumberOfResidents(reservation.getMaxNumberOfResidents()+newDoubleRoom.getMaxNumberOfResidents());
        if (reservation.getMaxNumberOfResidents()>=reservation.getOccupants()){
            reservation.setComplete(true);
        }else
            reservation.setComplete(false);

        Set<DoubleRoom> doubleRooms = new HashSet<>();
        doubleRooms.add(newDoubleRoom);
        reservation.newDoubleRooms(newDoubleRoom);
        doubleRoomCRUDRepository.save(newDoubleRoom);
    }

    public void put (DoubleRoom doubleRoom) {
        doubleRoomCRUDRepository.save(doubleRoom);
    }
    public void delete (int id){
        doubleRoomCRUDRepository.deleteById(id);
    }
}
