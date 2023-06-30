package com.atemos.sample.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
 
import lombok.Data;

@Entity
@Table(name="AMR_INFO")
@Data

public class AmrInfo {

	@Id
    @Column(name = "SERVER_ID")
    private int serverId;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PORT")
    private int port;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "URL")
    private String url;
}
