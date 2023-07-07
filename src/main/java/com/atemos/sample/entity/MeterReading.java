package com.atemos.sample.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="METER_READING") 
@Data
public class MeterReading {

	@EmbeddedId
    private MeterReadingId id;

    @ManyToOne
    @JoinColumn(name = "METER_ID", insertable = false, updatable = false)
    private Meter meter;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "MR_SCS_YN")
    private String success;

    @Column(name = "Voltage_A")
    private Float voltageA;

    @Column(name = "Voltage_B")
    private Float voltageB;

    @Column(name = "Voltage_C")
    private Float voltageC;

    @Column(name = "Ampare_A")
    private Float ampareA;

    @Column(name = "Ampare_B")
    private Float ampareB;

    @Column(name = "Ampare_C")
    private Float ampareC;

    @Column(name = "Voltage")
    private Float voltage;

    @Column(name = "Ampare")
    private Float ampare;

    @Column(name = "PF_A")
    private Float pfA;

    @Column(name = "PF_B")
    private Float pfB;

    @Column(name = "PF_C")
    private Float pfC;

    @Column(name = "PF")
    private Float pf;

    @Column(name = "Hz")
    private Float hz;

    @Column(name = "KWH")
    private Float kwh;
}
