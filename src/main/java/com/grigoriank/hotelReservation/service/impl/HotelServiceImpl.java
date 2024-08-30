package com.grigoriank.hotelReservation.service.impl;

import com.grigoriank.hotelReservation.entity.Hotel;
import com.grigoriank.hotelReservation.repository.HotelRepository;
import com.grigoriank.hotelReservation.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public void saveOrUpdateHotel(Hotel hotel) {
        hotelRepository.save(hotel);
    }

    @Override
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> getHotelsByCity(String city) {
        return hotelRepository.getHotelByCity(city);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }
}
