package com.example.demo.repositories.rooms;


import com.example.demo.model.rooms.FamilyRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRoomCRUDRepository extends CrudRepository<FamilyRoom, Integer> {

    @Query("SELECT f FROM FamilyRoom f WHERE f.isAvailable=true")
    List<FamilyRoom> findAvailable();
}
