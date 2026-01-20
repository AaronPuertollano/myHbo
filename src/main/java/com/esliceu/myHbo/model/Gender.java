package com.esliceu.myHbo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gender")
public class Gender {

    @Id
    @Column(name = "gender_id")
    private Integer id;

    @Column(name = "gender")
    private String gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
