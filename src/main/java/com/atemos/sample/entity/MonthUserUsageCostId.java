package com.atemos.sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class MonthUserUsageCostId implements Serializable {

    @Column(name = "CUSTOMER_ID")
    private int customerId;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "MONTH")
    private int month;

    // constructors, getters, and setters
    // ...
}
