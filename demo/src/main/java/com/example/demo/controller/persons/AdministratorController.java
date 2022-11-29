package com.example.demo.controller.persons;

import com.example.demo.model.persons.Administrator;
import com.example.demo.services.persons.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdministratorController {

    @Autowired
    AdministratorService administratorService;

    @CrossOrigin
    @PostMapping("/admins")
    public void postAdministrator(@RequestBody Administrator administrator){
        administratorService.post(administrator);
    }

    @CrossOrigin
    @GetMapping("/admins")
    public List<Administrator> getAllAdministrators(){
        List<Administrator> administratorList = administratorService.getAllAdministrators();

        return administratorList;
    }

    @CrossOrigin
    @GetMapping("/admins/{id}")
    public Administrator getAdministratorById(@PathVariable int id){
        return administratorService.get(id);
    }

    @CrossOrigin
    @PutMapping("/admins/{id}")
    public void putAdministrator(@RequestBody Administrator administrator){
        administratorService.put(administrator);
    }

    @CrossOrigin
    @DeleteMapping("/admins/{id}")
    public void deleteAdministrator(@PathVariable int id) {administratorService.delete(id);}
}
