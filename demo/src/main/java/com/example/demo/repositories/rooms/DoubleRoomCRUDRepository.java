package com.example.demo.repositories.rooms;


import com.example.demo.model.rooms.DoubleRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoubleRoomCRUDRepository extends CrudRepository<DoubleRoom, Integer> {

    @Query("SELECT d FROM DoubleRoom d WHERE d.isAvailable=true")
    List<DoubleRoom> findAvailable();

}
