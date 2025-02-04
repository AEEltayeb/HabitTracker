package com.example.service;

import com.example.model.Habit;
import com.example.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    // Get all habits
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    // Get habit by ID
    public Optional<Habit> getHabitById(Long id) {
        return habitRepository.findById(id);
    }

    // Create or update a habit
    public Habit saveHabit(Habit habit) {
        return habitRepository.save(habit);
    }

    // Delete a habit
    public void deleteHabit(Long id) {
        habitRepository.deleteById(id);
    }
}