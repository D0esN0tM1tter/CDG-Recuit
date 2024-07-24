package com.cdg.recruit.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChallengeDto {

    private Integer id ;

    private String title ;

    private String difficulty ;

    private String description ;
}
