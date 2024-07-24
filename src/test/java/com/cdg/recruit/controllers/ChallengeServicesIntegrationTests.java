package com.cdg.recruit.controllers;

import com.cdg.recruit.models.dtos.ChallengeDto;
import com.cdg.recruit.services.ChallengeServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class ChallengeServicesIntegrationTests {

    private final ChallengeServices challengeServices ;
    private final MockMvc mockMvc ;
    private final ObjectMapper objectMapper ;

    @Autowired
    public ChallengeServicesIntegrationTests(ChallengeServices challengeServices, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.challengeServices = challengeServices;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }


    @Test
    public  void testCreate() throws Exception {
        // Create and make a challenge persist  :
        ChallengeDto challengeDto = ChallengeDto.builder()
                .id(123)
                .title("Two sum")
                .difficulty("Easy")
                .description("DESCRIPTION")
                .build() ;

        challengeServices.create(challengeDto) ;
        String json = objectMapper.writeValueAsString(challengeDto) ;

        mockMvc.perform(MockMvcRequestBuilders.post("/challenges")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testFindAll() throws Exception {
        // Create and persist :
        ChallengeDto challengeDto1 = ChallengeDto.builder()
                .title("Two sum")
                .difficulty("Easy")
                .description("Desc 1")
                .build() ;

        ChallengeDto challengeDto2 = ChallengeDto.builder()
                .title("Bubble sort")
                .difficulty("medium")
                .description("Desc 2")
                .build() ;

        challengeServices.create(challengeDto1) ;
        challengeServices.create(challengeDto2) ;

        mockMvc.perform(MockMvcRequestBuilders.get("/challenges")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(challengeDto1.getTitle())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].description").value(challengeDto1.getDescription())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].difficulty").value(challengeDto1.getDifficulty())

        );
    }

    @Test
    public void testFindOne() throws Exception {

        // Create and persist :
        ChallengeDto challengeDto1 = ChallengeDto.builder()
                .title("Two sum")
                .difficulty("Easy")
                .description("Desc 1")
                .build() ;

        challengeServices.create(challengeDto1) ;

        mockMvc.perform(MockMvcRequestBuilders.get("/challenges/1")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(challengeDto1.getTitle())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.description").value(challengeDto1.getDescription())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.difficulty").value(challengeDto1.getDifficulty())

        );
    }


    @Test
    public void TestFindByDifficulty() throws Exception {


        // Create and persist :
        ChallengeDto challengeDto1 = ChallengeDto.builder()
                .title("Two sum")
                .difficulty("Easy")
                .description("Desc 1")
                .build() ;

        challengeServices.create(challengeDto1) ;

        mockMvc.perform(MockMvcRequestBuilders.get("/challenges/difficulty/" + challengeDto1.getDifficulty())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(challengeDto1.getTitle())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].description").value(challengeDto1.getDescription())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].difficulty").value(challengeDto1.getDifficulty())

        );

    }


    @Test
    public void testFindByTitle() throws Exception {


        // Create and persist :
        ChallengeDto challengeDto1 = ChallengeDto.builder()
                .title("Two sum")
                .difficulty("Easy")
                .description("Desc 1")
                .build() ;

        challengeServices.create(challengeDto1) ;

        mockMvc.perform(MockMvcRequestBuilders.get("/challenges/title/" + challengeDto1.getTitle())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(challengeDto1.getTitle())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.description").value(challengeDto1.getDescription())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.difficulty").value(challengeDto1.getDifficulty())

        );

    }


}
