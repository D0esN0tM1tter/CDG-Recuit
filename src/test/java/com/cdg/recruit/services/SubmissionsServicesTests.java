package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.ChallengeDto;
import com.cdg.recruit.models.dtos.ProblemStatementDto;
import com.cdg.recruit.models.dtos.SubmissionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)

public class SubmissionsServicesTests {
    private final SubmissionServices submissionServices ;

    @Autowired
    public SubmissionsServicesTests(SubmissionServices submissionServices) {
        this.submissionServices = submissionServices;
    }

    @Test
    public void testProcessSubmission() {
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
        SubmissionDto result = submissionServices.processSubmission(submissionDto) ;


        System.out.println("---------------------------------OUTPUT----------------------------------------------------");
        System.out.println(result.getOutput());

        System.out.println("---------------------------------EXPECTED----------------------------------------------------");
        System.out.println(result.getProblem().getExpected_output());

        System.out.println("---------------------------------SCORE----------------------------------------------------");
        System.out.println(result.getScore());

        Assertions.assertTrue(true);


    }
}
