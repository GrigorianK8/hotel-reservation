package com.grigoriank.hotelReservation.service;

import com.grigoriank.hotelReservation.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    void saveOrUpdateRoom(Room room);

    List<Room> getAllRooms();

    Optional<Room>  getRoomById(Long id);

    Optional<Room> getRoomByRoomNumber(String roomNumber);

    void deleteRoom(Long id);
}
