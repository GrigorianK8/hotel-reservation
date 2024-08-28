package com.grigoriank.hotelReservation.service.impl;

import com.grigoriank.hotelReservation.entity.Room;
import com.grigoriank.hotelReservation.repository.RoomRepository;
import com.grigoriank.hotelReservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public void saveOrUpdateRoom(Room room) {
        roomRepository.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Optional<Room> getRoomByRoomNumber(String roomNumber) {
        return roomRepository.getRoomByRoomNumber(roomNumber);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
