package com.jpa.crud.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "emp")
@Getter
@Setter
public class Employee {

    @Id
    private long id;
    private String first_name;
    private String last_name;
    private String phone;
    private String email;
    private String street;
    private String city;
    private String state;
    private Long zip_code;
}
