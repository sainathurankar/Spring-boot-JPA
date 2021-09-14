package com.jpa.crud.controller;

import com.jpa.crud.entity.Employee;
import com.jpa.crud.exception.BusinessException;
import com.jpa.crud.exception.ControllerException;
import com.jpa.crud.service.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees")
public class Controller {

    @Autowired
    private EmployeeServiceInterface employeeService;

    @GetMapping(path = "/show")
    public ResponseEntity<?> getAllEmployee(){
        System.out.println("getting all employees");
        Map map = new HashMap<String,List<Employee>>();
        map.put("employees",employeeService.getEmployees());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee){
        try {
            Employee savedEmployee = employeeService.addEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }
        catch (BusinessException e)
        {
            return new ResponseEntity<>(new ControllerException(e.getErrorCode(),e.getErrorMessage()),HttpStatus.BAD_REQUEST);
        }
        catch (ControllerException e) {
            ControllerException exception = new ControllerException("611","Something went wrong in Controller");
            return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/show/{id}")
    public ResponseEntity<?> showEmployeeById(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(employeeService.getById(Long.parseLong(id)),HttpStatus.OK);
        }
        catch (BusinessException e){
            return new ResponseEntity<>(new ControllerException(e.getErrorCode(),e.getErrorMessage()).getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (NumberFormatException e)
        {
            return new ResponseEntity<>("Please enter Number",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            ControllerException exception = new ControllerException("611","Something went wrong in Controller " + e.toString());
            return new ResponseEntity<>(exception.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("filter/{firstName}")
    public ResponseEntity<?> filterByName(@PathVariable("firstName") String firstName)
    {
        try {
            return new ResponseEntity<>(employeeService.filter(firstName),HttpStatus.OK);
        }
        catch (BusinessException e){
            return new ResponseEntity<>(e.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable String id){
        try {
            employeeService.delete(Long.parseLong(id));
            return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
        }
        catch (BusinessException e){
            return new ResponseEntity<>(new ControllerException(e.getErrorCode(),e.getErrorMessage()).getErrorMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (NumberFormatException e)
        {
            return new ResponseEntity<>("Please enter number",HttpStatus.BAD_REQUEST);
        }
        catch (Exception e)
        {
            ControllerException exception = new ControllerException("611","Something went wrong in Controller: " + e.toString());
            return new ResponseEntity<>(exception.getErrorMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @PutMapping(path = "/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

}
