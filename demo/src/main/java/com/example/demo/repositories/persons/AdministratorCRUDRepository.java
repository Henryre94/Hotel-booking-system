package com.example.demo.repositories.persons;

import com.example.demo.model.persons.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorCRUDRepository extends CrudRepository<Administrator, Integer> {
}
