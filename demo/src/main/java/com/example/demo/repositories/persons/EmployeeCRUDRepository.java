package com.example.demo.repositories.persons;

import com.example.demo.model.persons.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeCRUDRepository extends CrudRepository<Employee, Integer> {
}
