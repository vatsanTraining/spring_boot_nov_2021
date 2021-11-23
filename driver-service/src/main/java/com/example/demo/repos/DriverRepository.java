package com.example.demo.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Driver;

import java.time.LocalDate;
import java.util.*;

import javax.transaction.Transactional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

	//DSL 
	
	List<Driver> findByFirstName(String srchName);
	
	//DSL with Pagination
	Page<Driver> findByBirthDateBetween(LocalDate startDate,LocalDate endDate,Pageable page);
	
	// Customer Query with native sql query
	@Query(nativeQuery = true, 
			value = "update drivers_2021 set mobilenumber=:latestNumber where id=:srchId")
	@Modifying
	@Transactional
	int updateMobileNumber(@Param("srchId") long id, @Param("latestNumber") long latestNumber);
	
}
