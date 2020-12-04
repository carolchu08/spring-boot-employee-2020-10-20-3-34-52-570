package com.thoughtworks.springbootemployee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
@Document
public class Company {
    //@MongoId(FieldType.OBJECT_ID)
    @Id
    private String companyID;
    private String companyName;

    public Company(String companyName, String companyID) {
        this.companyName = companyName;
        this.companyID = companyID;
    }
    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company() {
    }

    public String getCompanyName() {
        return companyName;
    }



    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

}
