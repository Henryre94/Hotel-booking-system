package com.example.demo.services.persons;

import com.example.demo.dtos.CostumerInfo;
import com.example.demo.model.persons.Customer;
import com.example.demo.model.reservations.Reservation;
import com.example.demo.repositories.persons.CustomerCRUDRepository;
import com.example.demo.repositories.reservations.ReservationCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerCRUDRepository customerCRUDRepository;

    @Autowired
    ReservationCRUDRepository reservationCRUDRepository;

    public void post(Customer customer){
        customerCRUDRepository.save(customer);
    }

    public List<Customer> getAllCostumers(){
        return ((List<Customer>) customerCRUDRepository.findAll());

    }

    public Customer get (int id){
        Customer customer =  customerCRUDRepository.findById(id).get();
        return customer;
    }

    public List<CostumerInfo> getCostumerInfo(){
        return ((List<Customer>) customerCRUDRepository
                .findAll())
                // Is a Sequence of objects in this case all the Objects from the List<Customer>
                .stream()
                // .map is a method from stream used to apply a given function to the elements of the stream
                .map(this::convertToCostumerInfo)
                //Used to return the result of the operations done in the Stream
                .collect(Collectors.toList());
    }
    // This method is used to save the information needed in the DTO. The method is applied to every Object in the stream
    private CostumerInfo convertToCostumerInfo(Customer customer){
        CostumerInfo costumerInfo = new CostumerInfo();
        costumerInfo.setFirstName(customer.getFirstName());
        costumerInfo.setLastName(customer.getLastName());
        costumerInfo.setCheckIn(customer.getCheckIn());
        costumerInfo.setCheckOut(customer.getCheckOut());
        return costumerInfo;
    }

    public void put (Customer customer) {
        customerCRUDRepository.save(customer);
    }

    public void delete (int id){
        customerCRUDRepository.deleteById(id);
    }
}
