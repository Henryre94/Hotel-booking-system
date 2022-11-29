package com.example.demo.repositories.rooms;


import com.example.demo.model.rooms.SingleRoom;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleRoomCRUDRepository extends CrudRepository<SingleRoom, Integer> {

    @Query("SELECT s FROM SingleRoom s WHERE s.isAvailable=true")
    List<SingleRoom> findAvailable();

}