package com.cdg.recruit.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProblemStatementDto {

    private Integer id ;
    private ChallengeDto challenge ;
    private String language  ;
    private String statement ;
    private String expected_output ;
}
