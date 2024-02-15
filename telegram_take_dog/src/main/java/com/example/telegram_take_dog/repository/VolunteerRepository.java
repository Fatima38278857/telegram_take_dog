package com.example.telegram_take_dog.repository;

import com.example.telegram_take_dog.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
