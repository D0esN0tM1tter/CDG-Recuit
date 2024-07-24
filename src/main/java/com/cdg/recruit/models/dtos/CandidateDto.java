package com.cdg.recruit.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CandidateDto {

    private String cin ;
    private String name ;
    private String email ;
    private String status ;

}
