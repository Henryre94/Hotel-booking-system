package com.example.demo.controller.rooms;

import com.example.demo.dtos.RoomStatus;
import com.example.demo.model.rooms.FamilyRoom;
import com.example.demo.services.rooms.FamilyRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class FamilyRoomController {

    @Autowired
    FamilyRoomService familyRoomService;

    @CrossOrigin
    @PostMapping("/familyRooms")
    public void postFamilyRooms(@RequestBody FamilyRoom familyRoom){
        familyRoomService.post(familyRoom);
    }


    @GetMapping("/familyRooms")
    public List<FamilyRoom> getAllFamilyRooms(){
        List<FamilyRoom> familyRooms = familyRoomService.getAllFamilyRooms();

        return familyRooms;
    }

    @CrossOrigin
    @GetMapping("/familyRooms/{roomId}")
    public FamilyRoom getFamilyRoomById(@PathVariable int roomId){
        return familyRoomService.get(roomId);
    }

    @CrossOrigin
    @GetMapping("/familyRooms/{id}/available")
    //The ID is from Costumer to be able to extract the CheckIn and CheckOut dates, that are necessary to identify rooms
    //availability
    public List<FamilyRoom> getAvailableFamilyRooms(@PathVariable int id){
        List<FamilyRoom> familyRooms = familyRoomService.getAllAvailableRooms(id);

        return  familyRooms;
    }
    @GetMapping("/familyRooms/info")
    @ResponseBody
    public List<RoomStatus> getAllFamilyRoomsInformation(){
        List <RoomStatus> roomStatusList =familyRoomService.getAllFamilyRoomsStatus();

        return roomStatusList;
    }
    @CrossOrigin
    @PutMapping("/familyRooms")
    public void putFamilyRooms(@RequestBody FamilyRoom familyRoom){
        familyRoomService.put(familyRoom);
    }

    @CrossOrigin
    @PutMapping("/familyRooms/{reservationId}/{roomId}/add")
    public void bindFamilyRoomIntoReservation ( @PathVariable int reservationId, @PathVariable int roomId) {
        familyRoomService.bindFamilyRoomToReservation(reservationId, roomId);
    }

    @CrossOrigin
    @DeleteMapping("/familyRooms/{roomId}")
    public void deleteFamilyRooms(@PathVariable int roomId) {familyRoomService.delete(roomId);}
}
