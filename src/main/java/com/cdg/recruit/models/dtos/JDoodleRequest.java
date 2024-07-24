package com.cdg.recruit.models.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class JDoodleRequest {

    private String clientId;
    private String clientSecret;
    private String script;
    private String language;
    private String versionIndex;
}
