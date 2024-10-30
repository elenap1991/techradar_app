package com.techradar.polls.techradar_polls.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "technologies")
public class Technology {
    @Id
    @Column(name = "tech_id")
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "sec_id")
    private Section section;

    @ManyToOne
    @JoinColumn(name = "ring_id")
    private Ring ring;

    @ManyToOne
    @JoinColumn(name = "stat_id")
    private Status status;

    private LocalDateTime update_time;
}
