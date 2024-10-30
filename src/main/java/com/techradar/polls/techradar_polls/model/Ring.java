package com.techradar.polls.techradar_polls.model;

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
    private long ring_id;

    private String ring_name;
}
