package com.cdg.recruit.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class JDoodleResponse {

    private String output;
    private String statusCode;
    private String memory;
    private String cpuTime;
}
