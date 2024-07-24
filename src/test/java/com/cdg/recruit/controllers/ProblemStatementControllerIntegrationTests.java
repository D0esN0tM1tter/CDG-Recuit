package com.cdg.recruit.controllers;

import com.cdg.recruit.models.dtos.ChallengeDto;
import com.cdg.recruit.models.dtos.ProblemStatementDto;
import com.cdg.recruit.services.ProblemStatementServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
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
@Log

public class ProblemStatementControllerIntegrationTests {

    private final ProblemStatementServices problemStatementServices ;
    private final MockMvc mockMvc ;
    private final ObjectMapper objectMapper ;

    @Autowired
    public ProblemStatementControllerIntegrationTests(ProblemStatementServices problemStatementServices, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.problemStatementServices = problemStatementServices;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testCreation() throws Exception {
        // Create a challenge :
        ChallengeDto challengeDto = ChallengeDto.builder()
                .description("desc")
                .title("title")
                .difficulty("easy")
                .build() ;

        // Create a problem statament :
        ProblemStatementDto problemStatementDto = ProblemStatementDto.builder()
                .challenge(challengeDto)
                .language("java")
                .statement("java statement")
                .expected_output("{1 , 2 , 3 }")
                .build() ;

        String json = objectMapper.writeValueAsString(problemStatementDto) ;

        mockMvc.perform(MockMvcRequestBuilders.post("/problems")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );


    }


    @Test
    public void testFindByLanguageAndChallengeTitle() throws Exception {

        // Create a challenge :
        ChallengeDto challengeDto = ChallengeDto.builder()
                .description("desc")
                .title("title")
                .difficulty("easy")
                .build() ;


        ProblemStatementDto problemStatementDto = ProblemStatementDto.builder()
                .challenge(challengeDto)
                .language("java")
                .statement("java statement")
                .expected_output("{1 , 2 , 3 }")
                .build() ;


        problemStatementServices.create(problemStatementDto) ;

        mockMvc.perform(MockMvcRequestBuilders.get("/problems/title/java")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.language").value(problemStatementDto.getLanguage())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.statement").value(problemStatementDto.getStatement())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.expected_output").value(problemStatementDto.getExpected_output())

        );

    }


    @Test
    public void testUpdate() throws Exception {

        // Create a challenge :
        ChallengeDto challengeDto = ChallengeDto.builder()
                .description("desc")
                .title("Two Sum")
                .difficulty("easy")
                .build() ;


        ProblemStatementDto problemStatementDto = ProblemStatementDto.builder()
                .challenge(challengeDto)
                .language("Python")
                .statement("Python statement")
                .expected_output("{1 , 2 , 3 }")
                .build() ;

        ProblemStatementDto problemStatementDto1 = ProblemStatementDto.builder()
                .challenge(challengeDto)
                .language("Python")
                .statement("UPDATED statement")
                .expected_output("{UPDATED , 2 , 3 }")
                .build() ;

        problemStatementServices.create(problemStatementDto) ;


        String json = objectMapper.writeValueAsString(problemStatementDto1) ;


        mockMvc.perform(MockMvcRequestBuilders.put("/problems/" + challengeDto.getTitle() + "/" + problemStatementDto1.getLanguage())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.statement").value(problemStatementDto1.getStatement())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.expected_output").value(problemStatementDto1.getExpected_output())

        );

    }

    @Test
    public void testPartialUpdate() throws Exception {

        // Create a challenge :
        ChallengeDto challengeDto = ChallengeDto.builder()
                .description("desc")
                .title("Two Sum")
                .difficulty("easy")
                .build() ;


        ProblemStatementDto problemStatementDto = ProblemStatementDto.builder()
                .challenge(challengeDto)
                .language("Python")
                .statement("Python statement")
                .expected_output("{1 , 2 , 3 }")
                .build() ;

        problemStatementServices.create(problemStatementDto) ;

        mockMvc.perform(MockMvcRequestBuilders.patch("/problems/" + problemStatementDto.getChallenge().getTitle() +"/" + problemStatementDto.getLanguage() + "/UPDATED STATEMENT/UPDATED EXPECTED VALUE")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.statement").value("UPDATED STATEMENT")

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.expected_output").value("UPDATED EXPECTED VALUE")

        );

    }

}
