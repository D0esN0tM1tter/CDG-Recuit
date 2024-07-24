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
@Table(name = "submissions")

public class SubmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @ManyToOne(targetEntity = CandidateEntity.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id")
    private CandidateEntity candidate ;

    @ManyToOne(targetEntity = ProblemStatementEntity.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "problem_id")
    private ProblemStatementEntity problem ;
    private String codeContent ;
    private String output ;
    private Double score  ;

}
