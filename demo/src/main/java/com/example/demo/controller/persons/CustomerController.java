package com.example.demo.controller.persons;

import com.example.demo.dtos.CostumerInfo;
import com.example.demo.model.persons.Customer;
import com.example.demo.services.persons.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @CrossOrigin
    @PostMapping("/customers/post")
    public void post(@RequestBody Customer customer){
        customerService.post(customer);
    }


    @CrossOrigin
    @GetMapping("/customers")
    public List<Customer> getAllCostumers(){
        List<Customer> customerList = customerService.getAllCostumers();

        return customerList;
    }

    @CrossOrigin
    @GetMapping("/customers/{id}")
    public Customer getCostumerByID(@PathVariable int id){
        return customerService.get(id);
    }


    @CrossOrigin
    @GetMapping("/customers/info")
    @ResponseBody
    public List<CostumerInfo> getAllEmployeesKeyInformation(){
        List <CostumerInfo> costumerInfoList = customerService.getCostumerInfo();

        return costumerInfoList;
    }

    @CrossOrigin
    @PutMapping("/customers/{id}")
    public void putCostumer(@RequestBody Customer customer){
        customerService.put(customer);
    }


    @CrossOrigin
    @DeleteMapping("/customers/{id}")
    public void deleteCostumer(@PathVariable int id) {
        customerService.delete(id);}
}


