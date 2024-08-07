package com.cdg.recruit.configuration;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper() ;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE) ;
        return modelMapper ;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
