# Spring-boot-JPA/Hibernate

Created Web API's using Spring boot to perform CRUD operations on Employee database.

https://springboot-employee-jpa.herokuapp.com

##Get Request

To get list of all employees
https://springboot-employee-jpa.herokuapp.com/employees/show

To get employee by id
https://springboot-employee-jpa.herokuapp.com/employees/show/1

##Post Request

To save Employee into data base
https://springboot-employee-jpa.herokuapp.com/employees/save

Example:
{
    "first_name": "Sandeep",
    "last_name": "U",
    "phone": null,
    "email": "lyndsey.bean@hotmail.com",
    "street": "769 West Road ",
    "city": "Fairport",
    "state": "NY",
    "zip_code": 14450
}


##Delete Request

To delete employee by id
https://springboot-employee-jpa.herokuapp.com/employees/delete/{id}

Note: Replace {id} by employee id

##PUT Request

To update employee 
https://springboot-employee-jpa.herokuapp.com/employees/update
