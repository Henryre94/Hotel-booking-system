package com.example.demo.controller.rooms;

import com.example.demo.dtos.RoomStatus;
import com.example.demo.model.rooms.SingleRoom;
import com.example.demo.services.rooms.SingleRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SingleRoomController {
    @Autowired
    SingleRoomService singleRoomService;
    @CrossOrigin
    @PostMapping("/singleRooms")
    public void postSingleRooms(@RequestBody SingleRoom singleRoom){
        singleRoomService.post(singleRoom);
    }

    @CrossOrigin
    @GetMapping("/singleRooms")
    public List<SingleRoom> getAllSingleRooms(){
        List<SingleRoom> singleRoomList = singleRoomService.getAllSingleRooms();

        return singleRoomList;
    }

    @CrossOrigin
    @GetMapping("/singleRooms/{roomId}")
    public SingleRoom getSingleRoomById(@PathVariable int roomId){
        return singleRoomService.get(roomId);
    }

    @CrossOrigin
    @GetMapping("/singleRooms/{id}/available")
    //The ID is from Costumer to be able to extract the CheckIn and CheckOut dates, that are necessary to identify rooms
    //availability
    public List<SingleRoom> getAvailableSingleRooms(@PathVariable int id){
        List<SingleRoom> singleRoomList = singleRoomService.getAllAvailableRooms(id);

        return  singleRoomList;
    }

    @GetMapping("/singleRooms/info")
    @ResponseBody
    public List<RoomStatus> getAllSingleRoomsInformation(){
        List <RoomStatus> roomStatusList =singleRoomService.getSingleRoomsStatus();

        return roomStatusList;
    }

    @CrossOrigin
    @PutMapping("/singleRooms")
    public void put(@RequestBody SingleRoom singleRoom){
        singleRoomService.put(singleRoom);
    }


    @CrossOrigin
    @PutMapping("/singleRooms/{reservationId}/{roomId}/add")
    public void bindSingleRoomIntoReservation ( @PathVariable int reservationId, @PathVariable int roomId) {
        singleRoomService.bindSingleRoomToReservation( reservationId, roomId);
    }

    @CrossOrigin
    @DeleteMapping("/singleRooms/{roomId}")
    public void deleteSingleRooms(@PathVariable int roomId) {singleRoomService.delete(roomId);}

}
