package com.cdg.recruit.services.impl;

import com.cdg.recruit.models.dtos.JDoodleRequest;
import com.cdg.recruit.models.dtos.JDoodleResponse;
import com.cdg.recruit.services.JDoodleServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
public class JDoodleServiceImpl implements JDoodleServices {

    private final RestTemplate restTemplate ;

    public JDoodleServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public JDoodleResponse processSubmission(String code, String language) {
        String clientId = JDoodleServices.clientId;
        String clientSecret = JDoodleServices.clientSecret;
        String apiUrl = JDoodleServices.apiUrl + "/execute";

        JDoodleRequest jdoodleRequest = JDoodleRequest.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .language(language)
                .script(code)
                .versionIndex("0")
                .build();

        try {
            return restTemplate.postForObject(apiUrl, jdoodleRequest, JDoodleResponse.class);

        } catch (HttpClientErrorException e) {
            // Handle HTTP errors (4xx and 5xx responses)
            if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new RuntimeException("Invalid request to JDoodle API: " + e.getResponseBodyAsString());
            } else if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                throw new RuntimeException("Unauthorized access to JDoodle API: " + e.getResponseBodyAsString());
            } else {
                throw new RuntimeException("Error from JDoodle API: " + e.getResponseBodyAsString());
            }

        } catch (RestClientException e) {
            // Handle other errors such as network issues
            throw new RuntimeException("Failed to call JDoodle API: " + e.getMessage());

        } catch (Exception e) {
            // Handle any other unexpected errors
            throw new RuntimeException("Unexpected error: " + e.getMessage());
        }
    }


}
