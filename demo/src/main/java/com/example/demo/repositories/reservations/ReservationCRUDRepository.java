package com.example.demo.repositories.reservations;


import com.example.demo.model.reservations.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationCRUDRepository extends CrudRepository<Reservation, Integer> {


}
