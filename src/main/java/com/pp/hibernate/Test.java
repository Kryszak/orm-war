package com.pp.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test")
public class Test {

    @Id
    @Column(name = "test")
    private String test;

    public Test() {

    }

    public Test(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Test{" +
                "test='" + test + '\'' +
                '}';
    }
}
