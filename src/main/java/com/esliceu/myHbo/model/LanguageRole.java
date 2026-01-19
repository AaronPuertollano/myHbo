package com.esliceu.myHbo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "language_role")
public class LanguageRole {

    @Id
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "language_role")
    private String languageRole;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLanguageRole() {
        return languageRole;
    }

    public void setLanguageRole(String languageRole) {
        this.languageRole = languageRole;
    }
}
