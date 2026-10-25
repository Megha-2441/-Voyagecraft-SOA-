package com.voyagecraft.booking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    // Temporary storage for bookings
    private final List<Map<String, Object>> bookings = new ArrayList<>();


    // =========================
    // CREATE BOOKING
    // =========================
    @PostMapping
    public Map<String, Object> createBooking(
            @RequestBody Map<String, Object> booking) {

        bookings.add(booking);

        return Map.of(
            "message", "Booking created successfully",
            "booking", booking,
            "status", "PENDING_PAYMENT"
        );
    }


    // =========================
    // GET ALL BOOKINGS
    // =========================
    @GetMapping
    public Map<String, Object> getAllBookings() {

        return Map.of(
            "totalBookings", bookings.size(),
            "bookings", bookings
        );
    }


    // =========================
    // GET ALL REGISTERED USERS
    // =========================
    @GetMapping("/users")
    public Map<String, Object> getAllUsers() {

        List<Map<String, Object>> users = new ArrayList<>();

        for (Map<String, Object> booking : bookings) {

            users.add(Map.of(
                "username", booking.get("username"),
                "destination", booking.get("destination"),
                "numberOfPeople", booking.get("numberOfPeople")
            ));
        }

        return Map.of(
            "totalUsers", users.size(),
            "users", users
        );
    }


    // =========================
    // UPDATE BOOKING
    // =========================
    @PutMapping("/{username}")
    public Map<String, Object> updateBooking(
            @PathVariable String username,
            @RequestBody Map<String, Object> updatedBooking) {

        for (int i = 0; i < bookings.size(); i++) {

            Map<String, Object> booking = bookings.get(i);

            if (username.equals(booking.get("username"))) {

                bookings.set(i, updatedBooking);

                return Map.of(
                    "message", "Booking updated successfully",
                    "booking", updatedBooking
                );
            }
        }

        return Map.of(
            "message", "Booking not found",
            "username", username
        );
    }


    // =========================
    // DELETE BOOKING
    // =========================
    @DeleteMapping("/{username}")
    public Map<String, Object> deleteBooking(
            @PathVariable String username) {

        boolean removed = bookings.removeIf(
            booking -> username.equals(booking.get("username"))
        );

        if (removed) {

            return Map.of(
                "message", "Booking deleted successfully",
                "username", username
            );
        }

        return Map.of(
            "message", "Booking not found",
            "username", username
        );
    }
}