package com.techradar.polls.techradar_polls.jpa;

import com.techradar.polls.techradar_polls.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollsRepository extends JpaRepository <Poll, Long> {

}
