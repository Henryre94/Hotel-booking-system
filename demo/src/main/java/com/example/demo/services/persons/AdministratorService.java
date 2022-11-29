package com.example.demo.services.persons;

import com.example.demo.model.persons.Administrator;
import com.example.demo.repositories.persons.AdministratorCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {

    @Autowired
    AdministratorCRUDRepository administratorCRUDRepository;

    public void post(Administrator administrator){
        administratorCRUDRepository.save(administrator);
    }

    public Administrator get(int id){
        Administrator administrator = administratorCRUDRepository.findById(id).get();
        return administrator;
    }

    public List<Administrator> getAllAdministrators(){
        return ((List<Administrator>) administratorCRUDRepository.findAll());
    }

    public void put (Administrator administrator) {
        administratorCRUDRepository.save(administrator);
    }

    public void delete (int id){
        administratorCRUDRepository.deleteById(id);
    }

}
