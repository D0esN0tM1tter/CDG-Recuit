//package com.cdg.recruit.persistence;
//
//
//import com.cdg.recruit.models.entities.CandidateEntity;
//import com.cdg.recruit.models.entities.ChallengeEntity;
//import com.cdg.recruit.models.entities.SubmissionEntity;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//
//public class SubmissionTests {
//
//    private final SubmissionRepository submissionRepository;
//    private final CandidateRepository candidateRepository;
//    private final ChallengeRepository challengeRepository ;
//
//    @Autowired
//    public SubmissionTests(SubmissionRepository submissionRepository, CandidateRepository candidateRepository, ChallengeRepository challengeRepository) {
//        this.submissionRepository = submissionRepository;
//        this.candidateRepository = candidateRepository;
//        this.challengeRepository = challengeRepository;
//    }
//
//    @Test
//    public void testCandidateSelectionBasedOnASubmission() {
//        // Create a candidate and save it
//        CandidateEntity candidate = CandidateEntity.builder()
//                .cin("AD345566")
//                .name("Bilal")
//                .email("lahfaribilal2@gmail.com")
//                .status("failed")
//                .build();
//
//        candidateRepository.save(candidate) ;
//
//        // Create submission and associate it with the candidate :
//        SubmissionEntity submissionEntity = SubmissionEntity.builder()
//                .id(1)
//                .candidate(candidate)
//                .challenge(null)
//                .language("java")
//                .output("hello")
//                .codeContent("print")
//                .build() ;
//
//        // Save submission :
//        submissionRepository.save(submissionEntity) ;
//
//        Optional<CandidateEntity> result = submissionRepository.findCandidateBySubmissionId(submissionEntity.getId()) ;
//        Assertions.assertThat(result)
//                .isPresent() ;
//
//        Assertions.assertThat(result.get())
//                .isEqualTo(candidate) ;
//    }
//
//
//    @Test
//    public void testChallengeSelectionBySubmissionId () {
//        // Create a challenge :
//        ChallengeEntity challenge = ChallengeEntity.builder()
//                .title("Two Sum")
//                .description("Given an array of integers with size n , find the positions i and j of the first elements" +
//                        "that add up to the target value v")
//                .difficulty("Easy")
//                .build() ;
//
//        challengeRepository.save(challenge) ;
//
//        SubmissionEntity submission = SubmissionEntity.builder()
//                .id(1)
//                .candidate(null)
//                .challenge(challenge)
//                .language("java")
//                .output("hello")
//                .codeContent("print")
//                .build() ;
//
//        submissionRepository.save(submission) ;
//
//        // Retrieve the result :
//        Optional<ChallengeEntity> result = submissionRepository.findChallengeBySubmission(submission.getId()) ;
//        Assertions.assertThat(result).isPresent() ;
//        Assertions.assertThat(result.get()).isEqualTo(challenge) ;
//
//
//    }
//}
