package com.cdg.recruit.models.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionDto {

    private Integer id ;
    private CandidateDto candidate ;
    private ProblemStatementDto problem ;
    private String codeContent ;
    private String output ;
    private Double score ;
}
