package com.sayan.library.LibraryCMSService.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="history")
public class History implements Serializable{
	private static final long serialVersionUID = 890345L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long historyId;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private RequestTracker requestTracker;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "borrowed_at", nullable = false, updatable = false)
    @CreatedDate
    private Date borrowedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "returned_at", nullable = false)
    @LastModifiedDate
    private Date returnedAt;


}
