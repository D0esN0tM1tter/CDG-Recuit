package com.cdg.recruit.controllers;

import com.cdg.recruit.models.dtos.CandidateDto;
import com.cdg.recruit.services.CandidateServices;
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
public class CandidateControllerIntegrationTests {

    private final CandidateServices candidateServices;
    private final MockMvc mockMvc;
    private final ObjectMapper mapper;

    @Autowired
    public CandidateControllerIntegrationTests(CandidateServices candidateServices, MockMvc mockMvc, ObjectMapper mapper) {
        this.candidateServices = candidateServices;
        this.mockMvc = mockMvc;
        this.mapper = mapper;
    }

    @Test
    public void testCreationHttpStatus() throws Exception {
        // Create a candidate
        CandidateDto candidateDto = CandidateDto.builder()
                .cin("CIN")
                .email("EMAIL")
                .name("BILAL")
                .status("Status")
                .build();

        // Convert the object to JSON format
        String json = mapper.writeValueAsString(candidateDto);

        // Mock HTTP request
        mockMvc.perform(MockMvcRequestBuilders.post("/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCreationReturn() throws Exception {
        // Create a candidate
        CandidateDto candidateDto = CandidateDto.builder()
                .cin("CIN")
                .email("EMAIL")
                .name("BILAL")
                .status("Status")
                .build();

        // Convert the object to JSON format
        String json = mapper.writeValueAsString(candidateDto);

        // Mock HTTP request
        mockMvc.perform(MockMvcRequestBuilders.post("/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated()
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.cin").value(candidateDto.getCin())
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.name").value(candidateDto.getName())
                ).andExpect(
                       MockMvcResultMatchers.jsonPath("$.status").value(candidateDto.getStatus())
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.email").value(candidateDto.getEmail())

                );
    }


    @Test
    public void testList() throws Exception {

        // Create a candidate
        CandidateDto candidateDto = CandidateDto.builder()
                .cin("CIN")
                .email("EMAIL")
                .name("BILAL")
                .status("Status")
                .build();

        candidateServices.create(candidateDto) ;

        mockMvc.perform(MockMvcRequestBuilders.get("/candidates")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].cin").value(candidateDto.getCin())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value(candidateDto.getName())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].email").value(candidateDto.getEmail())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].status").value(candidateDto.getStatus())

        );

    }


    @Test
    public void testFindOne() throws Exception {

        CandidateDto candidateDto = CandidateDto.builder()
                .cin("CIN")
                .email("EMAIL")
                .name("BILAL")
                .status("Status")
                .build();

        candidateServices.create(candidateDto) ;

        mockMvc.perform(MockMvcRequestBuilders.get("/candidates/" + candidateDto.getCin())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.cin").value(candidateDto.getCin())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(candidateDto.getName())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value(candidateDto.getEmail())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.status").value(candidateDto.getStatus())

        );
    }

    @Test
    public void testUpdate() throws Exception {

        // Create a candidate
        CandidateDto candidateDto = CandidateDto.builder()
                .cin("CIN")
                .email("EMAIL")
                .name("BILAL")
                .status("Status")
                .build();

        CandidateDto candidateDto1 = CandidateDto.builder()
                .cin("UPDATED")
                .email("UPDATED")
                .name("UPDATED")
                .status("UPDATED")
                .build();

        candidateServices.create(candidateDto) ;
        candidateServices.update(candidateDto.getCin() , candidateDto1) ;


        mockMvc.perform(MockMvcRequestBuilders.get("/candidates/" + candidateDto.getCin())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.cin").value(candidateDto1.getCin())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(candidateDto1.getName())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value(candidateDto1.getEmail())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.status").value(candidateDto1.getStatus())

        );

    }

    @Test
    public void testDeleteWhenTheTargetDoesNotExist () throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/candidates/123")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        ) ;

    }

    @Test
    public void testDeleteWhenTargetExists() throws Exception {

        // Create a candidate
        CandidateDto candidateDto = CandidateDto.builder()
                .cin("CIN")
                .email("EMAIL")
                .name("BILAL")
                .status("Status")
                .build();

        // Save
        candidateServices.create(candidateDto) ;
        //
        mockMvc.perform(MockMvcRequestBuilders.delete("/candidates/" + candidateDto.getCin())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.cin").value(candidateDto.getCin())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(candidateDto.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.email").value(candidateDto.getEmail())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.status").value(candidateDto.getStatus()
                )) ;

    }




}
