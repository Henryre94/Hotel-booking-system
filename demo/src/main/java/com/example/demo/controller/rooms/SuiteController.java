package com.example.demo.controller.rooms;

import com.example.demo.dtos.RoomStatus;
import com.example.demo.model.rooms.Suite;
import com.example.demo.services.rooms.SuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuiteController {
    @Autowired
    SuiteService suiteService;

    @CrossOrigin
    @PostMapping("/suites")
    public void postSuite(@RequestBody Suite suite){
        suiteService.post(suite);
    }

    @CrossOrigin
    @GetMapping("/suites")
    public List<Suite> getAllSuites(){
        List<Suite> suiteList = suiteService.getAllSuites();

        return suiteList;
    }

    @CrossOrigin
    @GetMapping("/suites/{roomId}")
    public Suite getSuiteById(@PathVariable int roomId){
        return suiteService.get(roomId);
    }

    @CrossOrigin
    @GetMapping("/suites/{id}/available")
    //The ID is from Costumer to be able to extract the CheckIn and CheckOut dates, that are necessary to identify rooms
    //availability
    public List<Suite> getAvailableSuites(@PathVariable int id){
        List<Suite> suiteList = suiteService.getAllAvailableRooms(id);

        return  suiteList;
    }

    @CrossOrigin
    @GetMapping("/suites/info")
    @ResponseBody
    public List<RoomStatus> getAllSuitesInformation(){
        List <RoomStatus> roomStatusList =suiteService.getAllSuitesStatus();

        return roomStatusList;
    }

    @CrossOrigin
    @PutMapping("/suites/{roomId}")
    public void updateSuite(@RequestBody Suite suite){
        suiteService.put(suite);
    }

    @CrossOrigin
    @PutMapping("/suites/{reservationId}/{roomId}/add")
    public void bindSuiteIntoReservation (@PathVariable int reservationId, @PathVariable int roomId) {
        suiteService.bindSuiteToReservation(reservationId, roomId);
    }

    @CrossOrigin
    @DeleteMapping("/suites/{roomId}")
    public void deleteSuite(@PathVariable int roomId) {suiteService.delete(roomId);}
}
