package com.atemos.sample.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class MeterReadingId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "M_YEAR")
    private int year;

    @Column(name = "M_MONTH")
    private int month;

    @Column(name = "M_DAY")
    private int day;

    @Column(name = "M_TIME")
    private int time;

    @Column(name = "M_MINUTE")
    private int minute;

    @Column(name = "M_TIMESTAMP")
    private Timestamp timestamp;

    @Column(name = "METER_ID")
    private int meterId;
 
}
