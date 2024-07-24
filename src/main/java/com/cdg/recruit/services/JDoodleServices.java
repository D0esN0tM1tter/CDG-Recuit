package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.JDoodleResponse;

public interface JDoodleServices {

    public static final String clientId = "8e484e81f17d56269cb343482ee9e87a" ;
    public static final String clientSecret = "13a2546b19e43fe17b5c45415244425f6955429dcd0412d4271d794f5d8abd8b" ;
    public static final String apiUrl = "https://api.jdoodle.com/v1";
    public JDoodleResponse processSubmission(String code , String language) ;

}
