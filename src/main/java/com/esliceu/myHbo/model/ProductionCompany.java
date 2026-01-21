package com.esliceu.myHbo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "production_company")
public class ProductionCompany {

    @Id
    @Column(name = "company_id")
    private Integer id;

    @Column(name = "company_name")
    private String companyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
