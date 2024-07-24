package com.cdg.recruit.controllers;


import com.cdg.recruit.models.dtos.ChallengeDto;
import com.cdg.recruit.models.dtos.ProblemStatementDto;
import com.cdg.recruit.models.dtos.SubmissionDto;
import com.cdg.recruit.services.SubmissionServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxCursor;
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

public class SubmissionControllerIntegrationTests {
    private final SubmissionServices submissionServices ;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper ;

    @Autowired
    public SubmissionControllerIntegrationTests(SubmissionServices submissionServices, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.submissionServices = submissionServices;
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void testCreationAndProcessing() throws Exception {

        // Create a challenge :
        ChallengeDto challengeDto = ChallengeDto.builder()
                .id(123)
                .title("Add two numbers")
                .description("Add two numbers hhh")
                .difficulty("Easy")
                .build() ;

        // Create a problem statement :
        ProblemStatementDto problemStatementDto = ProblemStatementDto.builder()
                .id(111)
                .challenge(challengeDto)
                .expected_output("[3, 7, 121]")
                .statement("")
                .language("python3")
                .build() ;

        // Create a submission :
        String code = "def add( a , b) : \n" +
                "  return a + b \n" +
                "\n" +
                "if __name__ == \"__main__\" : \n" +
                "  result = [] \n" +
                "  result.append(add(1 , 2)) \n" +
                "  result.append(add(3 , 4)) \n" +
                "  result.append(add(55 , 66))\n" +
                "  print(result)" ;

        SubmissionDto submissionDto = SubmissionDto.builder()
                .id(123)
                .codeContent(code)
                .problem(problemStatementDto)
                .score(0.0)
                .output("")
                .candidate(null)
                .build() ;

        String json = objectMapper.writeValueAsString(submissionDto) ;

        mockMvc.perform(MockMvcRequestBuilders.post("/submissions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(submissionDto.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.codeContent").value(submissionDto.getCodeContent())

        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.score").value(100)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.output").value("[3, 7, 121]")

        );

    }

    @Test
    public void testFindAllSubmissions() {

    }
}
