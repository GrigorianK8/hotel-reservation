package com.grigoriank.hotelReservation.controller;

import com.grigoriank.hotelReservation.entity.Hotel;
import com.grigoriank.hotelReservation.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/new")
    public String showHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "hotel-form";
    }

    @PostMapping
    public String addHotel(Hotel hotel, BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "hotel-form";
        }
        hotelService.saveOrUpdateHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage", "Hotel added successfully!");
        return "redirect:/hotels";
    }

    @GetMapping
    public String getAllHotels(Model model) {
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "hotel-list";
    }

    @GetMapping("/{id}")
    public String getHotelById(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel is not found with id: " + id));
        model.addAttribute("hotel", hotel);
        return "hotel-details";
    }

    @GetMapping("/city/{city}")
    public String getHotelsByCity(@PathVariable String city, Model model) {
        List<Hotel> hotels = hotelService.getHotelsByCity(city);
        model.addAttribute("hotels", hotels);
        return "hotel-list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.getHotelById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hotel id:" + id));
        model.addAttribute("hotel", hotel);
        return "hotel-form";
    }

    @PostMapping("/edit/{id}")
    public String updateHotel(@PathVariable Long id, Hotel hotel, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "hotel-form";
        }
        hotel.setId(id);
        hotelService.saveOrUpdateHotel(hotel);
        redirectAttributes.addFlashAttribute("successMessage", "Hotel updated successfully!");
        return "redirect:/hotels";
    }
}
