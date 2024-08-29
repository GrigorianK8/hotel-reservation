package com.grigoriank.hotelReservation.controller;

import com.grigoriank.hotelReservation.entity.Room;
import com.grigoriank.hotelReservation.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public String addRoom(Room room, BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "room-form";
        }
        roomService.saveOrUpdateRoom(room);
        redirectAttributes.addFlashAttribute("successMessage", "Room added successfully!");
        return "redirect:/rooms";
    }

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "room-list";
    }

    @GetMapping("/new")
    public String showAddRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "room-form";
    }

    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id,
                              Model model) {
        Room room = roomService.getRoomById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room id:" + id));
        model.addAttribute("room", room);
        return "room-details";
    }

    @GetMapping("/room-number/{roomNumber}")
    public String getRoomByRoomNumber(@PathVariable String roomNumber,
                                      Model model) {
        Room room = roomService.getRoomByRoomNumber(roomNumber)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room number:" + roomNumber));
        model.addAttribute("room", room);
        return "room-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id,
                               Model model) {
        Room room = roomService.getRoomById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid room id:" + id));
        model.addAttribute("room", room);
        return "room-form";
    }

    @PostMapping("/edit/{id}")
    public String updateRoom(@PathVariable Long id,
                             RedirectAttributes redirectAttributes,
                             BindingResult result, Room room) {
        if (result.hasErrors()) {
            return "room-form";
        }
        room.setId(id);
        roomService.saveOrUpdateRoom(room);
        redirectAttributes.addFlashAttribute("successMessage", "Room updated successfully!");
        return "redirect:/rooms";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id,
                             RedirectAttributes redirectAttributes) {
        roomService.getRoomById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Room deleted successfully!");
        return "redirect:/rooms";
    }
}
