package com.example.demo.services.rooms;

import com.example.demo.dtos.RoomStatus;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.reservations.Reservation;
import com.example.demo.model.rooms.SingleRoom;
import com.example.demo.repositories.persons.CustomerCRUDRepository;
import com.example.demo.repositories.reservations.ReservationCRUDRepository;
import com.example.demo.repositories.rooms.SingleRoomCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.services.reservations.ReservationService.daysBetweenTwoDates;


@Service
public class SingleRoomService {
    @Autowired
    SingleRoomCRUDRepository singleRoomCRUDRepository;
    @Autowired
    ReservationCRUDRepository reservationCRUDRepository;
    @Autowired
    CustomerCRUDRepository customerCRUDRepository;

    public void post(SingleRoom singleRoom){
    }

    public List<SingleRoom> getAllSingleRooms(){
        return ((List<SingleRoom>) singleRoomCRUDRepository.findAll());

    }

    public SingleRoom get(int id){
        SingleRoom singleRoom = singleRoomCRUDRepository.findById(id).get();
        return singleRoom;
    }

    public List<SingleRoom> getAllAvailableRooms (int id){
        Customer newCostumer = customerCRUDRepository.findById(id).get();
        for(Reservation reservationList : reservationCRUDRepository.findAll()){
            if (!newCostumer.getCheckIn().isAfter(reservationList.getCheckIn()) && !newCostumer.getCheckOut().isBefore(reservationList.getCheckOut())) {
                for (SingleRoom singleRoom : reservationList.getSingleRooms()) {
                    singleRoom.setAvailable(false);
                    singleRoomCRUDRepository.save(singleRoom);
                }
            }
        }
        return ((List<SingleRoom>) singleRoomCRUDRepository.findAvailable());
    }
    public List<RoomStatus> getSingleRoomsStatus(){
        return ((List<SingleRoom>) singleRoomCRUDRepository
                .findAll())
                // Is a Sequence of objects in this case all the Objects from the List<FamilyRoom>
                .stream()
                // .map is a method from stream used to apply a given function to the elements of the stream
                .map(this::convertToRoomStatus)
                //Used to return the result of the operations done in the Stream
                .collect(Collectors.toList());
    }
    // This method is used to save the information needed in the DTO. The method is applied to every Object in the stream
    private RoomStatus convertToRoomStatus(SingleRoom singleRoom){
        RoomStatus roomStatus = new RoomStatus();
        roomStatus.setAvailable(singleRoom.isAvailable());
        roomStatus.setRoomNumber(singleRoom.getRoomNumber());
        roomStatus.setPrice(singleRoom.getPrice());
        return roomStatus;
    }


    public void put (SingleRoom singleRoom) {
     singleRoomCRUDRepository.save(singleRoom);
    }

    public void bindSingleRoomToReservation(int reservationId, int roomId){
        Reservation reservation = reservationCRUDRepository.findById(reservationId).get();
        SingleRoom newSingleRoom = singleRoomCRUDRepository.findById(roomId).get();

        long durationOfStay = daysBetweenTwoDates(reservation.getCheckIn(),reservation.getCheckOut());
        double singleRoomPrice = newSingleRoom.getPrice()*durationOfStay;

        reservation.setFinalPrice(reservation.getFinalPrice() + singleRoomPrice);
        reservation.setMaxNumberOfResidents(reservation.getMaxNumberOfResidents()+newSingleRoom.getMaxNumberOfResidents());
        if (reservation.getMaxNumberOfResidents()>=reservation.getOccupants()){
            reservation.setComplete(true);
        }else
            reservation.setComplete(false);

        Set<SingleRoom> singleRooms = new HashSet<>();
        singleRooms.add(newSingleRoom);
        reservation.newSingleRoom(newSingleRoom);
        singleRoomCRUDRepository.save(newSingleRoom);
    }

    public void delete (int id){
        singleRoomCRUDRepository.deleteById(id);
    }
}
