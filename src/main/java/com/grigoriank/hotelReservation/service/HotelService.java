package com.grigoriank.hotelReservation.service;

import com.grigoriank.hotelReservation.entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    void saveOrUpdateHotel(Hotel hotel);

    Hotel getHotelById(Long id);

    Optional<Hotel> getHotelByCity(String city);

    List<Hotel> getAllHotels();

    void deleteHotel(Long id);
}
