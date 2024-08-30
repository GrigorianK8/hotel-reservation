package com.grigoriank.hotelReservation.repository;

import com.grigoriank.hotelReservation.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    List<Hotel> getHotelByCity(String city);
}
