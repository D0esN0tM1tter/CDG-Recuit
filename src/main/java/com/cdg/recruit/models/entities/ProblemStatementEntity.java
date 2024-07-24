package com.cdg.recruit.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "problem_statements")

public class ProblemStatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @ManyToOne(targetEntity = ChallengeEntity.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "challenge_id")
    private ChallengeEntity challenge ;

    private String language  ;

    @Lob
    @Column(nullable = false , columnDefinition = "TEXT")
    private String statement ;

    private String expected_output ;


}
