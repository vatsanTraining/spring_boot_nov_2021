package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.demo.model.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "bookings_2021")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookingEntity {

	    @Id
	    @Column(name = "bookingid")
		private Long bookingid;

	    @Column(name = "startpoint")
        private String startPoint;
	    
	    @Column(name = "finishpoint")
	    private String finishPoint;
	    
	    @Column(name = "passengerid")
	    private Long passengerId;
	    
	    @Column(name = "driverid")
	    private Long driverId;

	    @Column(name = "creationdate")
		@DateTimeFormat(iso = ISO.DATE)
	    private LocalDateTime creationDate;

	    @Column(name = "status")
	    private BookingStatus status;

}
