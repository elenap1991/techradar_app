package com.techradar.polls.techradar_polls.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "rings")
public class Ring {
    @Id
    @Column(name = "ring_id")
    private long ringId;

    @Column(name = "ring_name")
    private String ringName;
}
