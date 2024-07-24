package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.CandidateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD
)
public class CandidateServiceIntegrationTests {

    private final CandidateServices candidateServices ;

    @Autowired
    public CandidateServiceIntegrationTests(CandidateServices candidateServices) {
        this.candidateServices = candidateServices;
    }


    @Test
    public void testCreationAndRetrieval() {
        // Create a candidate :
        CandidateDto candidate = CandidateDto.builder()
                .cin("AD345566")
                .name("Lahfari")
                .email("lahfaribilal@gmail.com")
                .status("")
                .build() ;

        // Insert the candidate into the database :
        candidateServices.create(candidate) ;
        // Retrieve the candidate :
        Optional<CandidateDto> result = candidateServices.findOne(candidate.getCin()) ;
        // Tests :
        Assertions.assertThat(result).isPresent() ;
        Assertions.assertThat(result.get()).isEqualTo(candidate) ;
    }
}
