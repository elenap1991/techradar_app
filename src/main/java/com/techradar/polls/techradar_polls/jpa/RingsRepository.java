package com.techradar.polls.techradar_polls.jpa;

import com.techradar.polls.techradar_polls.model.Ring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RingsRepository extends JpaRepository <Ring, Long> {
    @Query("select r from Ring r where r.ring_name = ?1")
    List<Ring> findByRingName(String ringName);
}
