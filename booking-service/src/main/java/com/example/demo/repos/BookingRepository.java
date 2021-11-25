package com.example.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.BookingEntity;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

	
}
