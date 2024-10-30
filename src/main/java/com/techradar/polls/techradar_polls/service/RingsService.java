package com.techradar.polls.techradar_polls.service;

import com.techradar.polls.techradar_polls.jpa.RingsRepository;
import com.techradar.polls.techradar_polls.model.Ring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class RingsService {
    @Autowired
    private RingsRepository ringsRepository;

    public Ring getRingByName(String ringName) {
        List<Ring> ring = ringsRepository.findByRingName(ringName);
        if (ring.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, "Invalid ring name");
        }
        return ring.get(0);
    }
}
