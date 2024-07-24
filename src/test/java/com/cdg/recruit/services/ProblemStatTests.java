package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.ChallengeDto;
import com.cdg.recruit.models.dtos.ProblemStatementDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)

public class ProblemStatTests {

    private final ProblemStatementServices problemStatementServices ;

    @Autowired
    public ProblemStatTests(ProblemStatementServices problemStatementServices) {
        this.problemStatementServices = problemStatementServices;
    }


    @Test
    public void testUpdate() {
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

        ProblemStatementDto problemStatementDto1 = ProblemStatementDto.builder()
                .challenge(challengeDto)
                .language("java")
                .statement("UPDATED statement")
                .expected_output("{UPDATED , 2 , 3 }")
                .build() ;

        problemStatementServices.create(problemStatementDto) ;

        Optional<ProblemStatementDto> result = problemStatementServices.updateByChalllengeTitleAndLanguage(problemStatementDto1.getChallenge().getTitle() , problemStatementDto1.getLanguage() , problemStatementDto1) ;

        Assertions.assertThat(result).isPresent( );
        Assertions.assertThat(result.get().getStatement()).isEqualTo(problemStatementDto1.getStatement()) ;
        Assertions.assertThat(result.get().getExpected_output()).isEqualTo(problemStatementDto1.getExpected_output()) ;


    }


    @Test
    public void testUpdateStatementAndExpectedOutput() {
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

        Optional<ProblemStatementDto> result = problemStatementServices.updateStatementAndExpectedOutput(problemStatementDto.getChallenge().getTitle() , problemStatementDto.getLanguage() , "NEW STATEMENT" , "NEW EXPECTED VALUE") ;

        Assertions.assertThat(result).isPresent( );
        Assertions.assertThat(result.get().getStatement()).isEqualTo("NEW STATEMENT") ;
        Assertions.assertThat(result.get().getExpected_output()).isEqualTo("NEW EXPECTED VALUE") ;


    }
}
