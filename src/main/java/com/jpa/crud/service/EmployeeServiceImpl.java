package com.jpa.crud.service;

import com.jpa.crud.entity.Employee;
import com.jpa.crud.exception.BusinessException;
import com.jpa.crud.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> getEmployees() {
        try {

            List<Employee> empList = repository.findAll();
            if (empList.isEmpty())
                throw new BusinessException("604", "List is empty. Nothing to return");
            return empList;
        }
        catch (Exception e)
        {
            throw new BusinessException("605", "Something went wrong in Service layer " + e.getLocalizedMessage());
        }

    }

    @Override
    public Employee addEmployee(Employee employee) {
        try {
            if(employee.getFirst_name().isEmpty() || employee.getFirst_name().length() ==0)
                throw new BusinessException("601","Please send proper name. It is blank");
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException("602","Employee object is null");
        }
        catch (Exception e)
        {
            throw new BusinessException("603","Something went wrong in Service layer while saving employee " + e.getLocalizedMessage());
        }
        return repository.save(employee);
    }

    @Override
    public Employee getById(Long id) {
        try {
            return repository.findById(id).get();
        }
        catch (IllegalArgumentException e)
        {
            throw new BusinessException("606","Given employee id is null");
        }
        catch (NoSuchElementException e)
        {
            throw new BusinessException("607", "Given id doesnt exist in database");
        }
    }

    @Override
    public void delete(long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e)
        {
            throw new BusinessException("607", "Given id doesnt exist in database ");
        }
        catch (Exception e)
        {
            throw new BusinessException("608", e.toString());
        }

    }

    @Override
    public List<Employee> filter(String c) {
        try {
//            if(c.isEmpty() || c.length()==0){
//                throw new BusinessException("609","Please enter something to filter");
//            }
            List<Employee> empList = repository.findAll();
            List<Employee> filteredEmp = empList.stream().filter(e -> e.getFirst_name().startsWith(c)).collect(Collectors.toList());
            if(filteredEmp.isEmpty() || filteredEmp.size()==0)
            {
                throw new BusinessException("610","No first_name found which starts with "+ c);
            }
            return filteredEmp;
        }
        catch (BusinessException e){
            throw new BusinessException("610",e.getErrorMessage());
        }

    }


}
