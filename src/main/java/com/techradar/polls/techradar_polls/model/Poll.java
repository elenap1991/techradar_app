package com.techradar.polls.techradar_polls.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@RequiredArgsConstructor
@Data
@Entity
@Table(name = "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pollId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "tech_id")
//    private Technologies technology;
    @NonNull
    private Long techId;

    @ManyToOne
    @JoinColumn(name = "ring_id")
    @NonNull
    private Ring ring;

    @NonNull
    private LocalDateTime time;
}
