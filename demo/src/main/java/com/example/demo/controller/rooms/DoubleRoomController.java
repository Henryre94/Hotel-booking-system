package com.example.demo.controller.rooms;


import com.example.demo.dtos.RoomStatus;
import com.example.demo.model.rooms.DoubleRoom;
import com.example.demo.services.rooms.DoubleRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoubleRoomController {
    @Autowired
    DoubleRoomService doubleRoomService;

    @CrossOrigin
    @PostMapping("/doubleRooms")
    public void postDoubleRoom(@RequestBody DoubleRoom doubleRoom){
        doubleRoomService.post(doubleRoom);
    }

    @GetMapping("/doubleRooms")
    public List<DoubleRoom> getAllDoubleRooms(){
        List<DoubleRoom> doubleRoomsList = doubleRoomService.getAllDoubleRooms();

        return doubleRoomsList;
    }
    @CrossOrigin
    @GetMapping("/doubleRooms/{roomId}")
    public DoubleRoom getDoubleRoomById(@PathVariable int roomId){
        return doubleRoomService.get(roomId);
    }

    @CrossOrigin
    @GetMapping("/doubleRooms/{id}/available")
    //The ID is from Costumer to be able to extract the CheckIn and CheckOut dates, that are necessary to identify rooms
    //availability
    public List<DoubleRoom> getAvailableDoubleRooms(@PathVariable int id){
        List<DoubleRoom> doubleRoomsList = doubleRoomService.getAllAvailableRooms(id);

        return  doubleRoomsList;
    }
    @CrossOrigin
    @GetMapping("/doubleRooms/info")
    @ResponseBody
    public List<RoomStatus> getAllDoubleRoomsInformation(){
        List <RoomStatus> roomStatusList =doubleRoomService.getDoubleRoomsStatus();

        return roomStatusList;
    }
    @CrossOrigin
    @PutMapping("/doubleRooms")
    public void updateDoubleRoom(@RequestBody DoubleRoom doubleRoom){
        doubleRoomService.put(doubleRoom);
    }

    @CrossOrigin
    @PutMapping("/doubleRooms/{reservationId}/{roomId}/add")
    public void bindDoubleRoomIntoReservation ( @PathVariable int reservationId, @PathVariable int roomId) {
        doubleRoomService.bindDoubleRoomToReservation( reservationId, roomId);
    }

    @CrossOrigin
    @DeleteMapping("/doubleRooms/{roomId}")
    public void deleteDoubleRoom(@PathVariable int roomId) {doubleRoomService.delete(roomId);}
}
