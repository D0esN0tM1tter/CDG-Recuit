package com.cdg.recruit.persistence;

import com.cdg.recruit.models.entities.ChallengeEntity;
import com.cdg.recruit.models.entities.ProblemStatementEntity;
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

    private final ProblemStatementRepository problemStatementRepository ;

    @Autowired
    public ProblemStatTests(ProblemStatementRepository problemStatementRepository) {
        this.problemStatementRepository = problemStatementRepository;
    }

    @Test
    public void testProblemStatRetrieval() {
        // Create a challenge :


        // Create a challenge :
        ChallengeEntity challenge = ChallengeEntity.builder()
                .description("desc")
                .title("title")
                .difficulty("easy")
                .build() ;

        ProblemStatementEntity problem = ProblemStatementEntity.builder().language("java")
                .challenge(challenge)
                .statement("java statement")
                .expected_output("{1 , 2 , 3 }")
                .build() ;

        problemStatementRepository.save(problem);


        Optional<ProblemStatementEntity> result = problemStatementRepository.findByChallengeTitleAndLanguage(challenge.getTitle() , problem.getLanguage()) ;

        Assertions.assertThat(result).isPresent() ;
        Assertions.assertThat(result.get()).isEqualTo(problem) ;

    }


}
