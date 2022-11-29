package com.example.demo.services.rooms;

import com.example.demo.dtos.RoomStatus;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.reservations.Reservation;
import com.example.demo.model.rooms.FamilyRoom;
import com.example.demo.repositories.persons.CustomerCRUDRepository;
import com.example.demo.repositories.reservations.ReservationCRUDRepository;
import com.example.demo.repositories.rooms.FamilyRoomCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.services.reservations.ReservationService.daysBetweenTwoDates;


@Service
public class FamilyRoomService {
    @Autowired
    FamilyRoomCRUDRepository familyRoomCRUDRepository;

    @Autowired
    ReservationCRUDRepository reservationCRUDRepository;

    @Autowired
    CustomerCRUDRepository customerCRUDRepository;

    public void post(FamilyRoom familyRoom){
        familyRoomCRUDRepository.save(familyRoom);
    }

    public List<FamilyRoom> getAllFamilyRooms(){
        return ((List<FamilyRoom>) familyRoomCRUDRepository.findAll());

    }

    public FamilyRoom get(int id){
        FamilyRoom familyRoom = familyRoomCRUDRepository.findById(id).get();
        return familyRoom;
    }

    public List<FamilyRoom> getAllAvailableRooms (int id){
        Customer newCostumer = customerCRUDRepository.findById(id).get();
        for(Reservation reservationList : reservationCRUDRepository.findAll()){
            if (!newCostumer.getCheckIn().isAfter(reservationList.getCheckIn()) && !newCostumer.getCheckOut().isBefore(reservationList.getCheckOut())) {
                for (FamilyRoom familyRoom : reservationList.getFamilyRooms()) {
                    familyRoom.setAvailable(false);
                    familyRoomCRUDRepository.save(familyRoom);
                }
            }
        }
        return ((List<FamilyRoom>) familyRoomCRUDRepository.findAvailable());
    }

    public List<RoomStatus> getAllFamilyRoomsStatus(){
        return ((List<FamilyRoom>) familyRoomCRUDRepository
                .findAll())
                // Is a Sequence of objects in this case all the Objects from the List<FamilyRoom>
                .stream()
                // .map is a method from stream used to apply a given function to the elements of the stream
                .map(this::convertToRoomStatus)
                //Used to return the result of the operations done in the Stream
                .collect(Collectors.toList());
    }
    // This method is used to save the information needed in the DTO. The method is applied to every Object in the stream
    private RoomStatus convertToRoomStatus(FamilyRoom familyRoom){
        RoomStatus roomStatus = new RoomStatus();
        roomStatus.setAvailable(familyRoom.isAvailable());
        roomStatus.setRoomNumber(familyRoom.getRoomNumber());
        roomStatus.setPrice(familyRoom.getPrice());
        return roomStatus;
    }

    public void bindFamilyRoomToReservation(int reservationId, int roomId){
        Reservation reservation = reservationCRUDRepository.findById(reservationId).get();
        FamilyRoom newFamilyRoom = familyRoomCRUDRepository.findById(roomId).get();

        long durationOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
        double familyRoomPrice =newFamilyRoom.getPrice()*durationOfStay;


        reservation.setFinalPrice(reservation.getFinalPrice() + familyRoomPrice);
        reservation.setMaxNumberOfResidents(reservation.getMaxNumberOfResidents()+newFamilyRoom.getMaxNumberOfResidents());
        if (reservation.getMaxNumberOfResidents()>=reservation.getOccupants()){
            reservation.setComplete(true);
        }else
            reservation.setComplete(false);

        Set<FamilyRoom> familyRooms = new HashSet<>();
        familyRooms.add(newFamilyRoom);
        reservation.newFamilyRoom(newFamilyRoom);
        familyRoomCRUDRepository.save(newFamilyRoom);
    }

    public void put (FamilyRoom familyRoom) {
        familyRoomCRUDRepository.save(familyRoom);
    }
    public void delete (int id){
        familyRoomCRUDRepository.deleteById(id);
    }
}
