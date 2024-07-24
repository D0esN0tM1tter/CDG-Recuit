package com.cdg.recruit.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "challenges")

public class ChallengeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    private String title ;

    private String difficulty ;

    @Lob
    @Column(nullable = false , columnDefinition = "TEXT")
    private String description ;



}
