package com.example.demo.controller.persons;

import com.example.demo.dtos.EmployeeInfo;
import com.example.demo.model.persons.Employee;
import com.example.demo.services.persons.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @CrossOrigin
    @PostMapping("/employees")
    public void postEmployee(@RequestBody Employee employee){
        employeeService.post(employee);
    }


    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = employeeService.getAllEmployees();

        return employeeList;
    }

    @CrossOrigin
    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return employeeService.get(id);
    }


    @CrossOrigin
    @GetMapping("/employees/info")
    @ResponseBody
    public List<EmployeeInfo> getAllEmployeesInformation(){
        List <EmployeeInfo> employeeInfoList =employeeService.getAllEmployeesInfo();

        return employeeInfoList;
    }

    @CrossOrigin
    @GetMapping("/employees/{id}/salary")
    public double getEmployeeSalary(@PathVariable int id) {
        return employeeService.getEmployeeSalary(id);}


    @CrossOrigin
    @PutMapping("/employees/{id}")
    public void putEmployee(@RequestBody Employee employee){

        employeeService.put(employee);
    }

    @CrossOrigin
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {employeeService.delete(id);}

}
