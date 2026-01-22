package com.esliceu.myHbo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    private Integer id;

    @Column(name = "person_name")
    private String personName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
