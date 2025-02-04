package com.example.controller;

import com.example.model.Habit;
import com.example.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    @Autowired
    private HabitService habitService;

    // Get all habits
    @GetMapping
    public List<Habit> getAllHabits() {
        return habitService.getAllHabits();
    }

    // Get habit by ID
    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id) {
        Optional<Habit> habit = habitService.getHabitById(id);
        return habit.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new habit
    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit) {
        Habit savedHabit = habitService.saveHabit(habit);
        return new ResponseEntity<>(savedHabit, HttpStatus.CREATED);
    }

    // Update an existing habit
    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody Habit habit) {
        Optional<Habit> existingHabit = habitService.getHabitById(id);
        if (existingHabit.isPresent()) {
            habit.setId(id);
            Habit updatedHabit = habitService.saveHabit(habit);
            return ResponseEntity.ok(updatedHabit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a habit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        habitService.deleteHabit(id);
        return ResponseEntity.noContent().build();
    }
}