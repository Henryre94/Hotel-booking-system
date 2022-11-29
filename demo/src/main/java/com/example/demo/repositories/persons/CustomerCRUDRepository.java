package com.example.demo.repositories.persons;

import com.example.demo.model.persons.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCRUDRepository extends CrudRepository<Customer, Integer> {
}
