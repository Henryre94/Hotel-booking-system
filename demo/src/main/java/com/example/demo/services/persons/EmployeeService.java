package com.example.demo.services.persons;

import com.example.demo.dtos.EmployeeInfo;
import com.example.demo.model.persons.Employee;
import com.example.demo.repositories.persons.EmployeeCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeCRUDRepository employeeCRUDRepository;

    public void post(Employee employee){
        employeeCRUDRepository.save(employee);
    }

    public List<Employee> getAllEmployees(){
        return ((List<Employee>) employeeCRUDRepository.findAll());
    }

    public Employee get(int id){
        Employee employee = employeeCRUDRepository.findById(id).get();
        return employee;
    }

    public double getEmployeeSalary(int id){
        Employee employee = employeeCRUDRepository.findById(id).get();
        if (employee.isBonus()==true) {
            employee.setSalary(employee.getSalary() * 1.01);
        }
            return employee.getSalary();

    }

    public List<EmployeeInfo> getAllEmployeesInfo(){
        return ((List<Employee>) employeeCRUDRepository
                .findAll())
                // Is a Sequence of objects in this case all the Objects from the List<Employee>
                .stream()
                // .map is a method from stream used to apply a given function to the elements of the stream
                .map(this::convertToEmployeeInfo)
                //Used to return the result of the operations done in the Stream
                .collect(Collectors.toList());
    }
    // This method is used to save the information needed in the DTO. The method is applied to every Object in the stream
    private EmployeeInfo convertToEmployeeInfo(Employee employee){
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setFirstName(employee.getFirstName());
        employeeInfo.setLastName(employee.getLastName());
        employeeInfo.setPosition(employee.getPosition());
        employeeInfo.setSalary(employee.getSalary());
        employeeInfo.setAdmin(employee.isAdmin());

        return employeeInfo;
    }


    public void put (Employee employee) {
        employeeCRUDRepository.save(employee);
    }

    public void delete (int id){
        employeeCRUDRepository.deleteById(id);
    }

}
