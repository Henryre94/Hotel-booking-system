package com.example.demo.repositories.rooms;



import com.example.demo.model.rooms.Suite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuiteCRUDRepository extends CrudRepository<Suite, Integer> {
    @Query("SELECT t FROM Suite t WHERE t.isAvailable=true")
    List<Suite> findAvailable();
}
