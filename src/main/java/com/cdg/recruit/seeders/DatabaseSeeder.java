package com.cdg.recruit.seeders;

import com.cdg.recruit.models.entities.CandidateEntity;
import com.cdg.recruit.models.entities.ChallengeEntity;
import com.cdg.recruit.models.entities.ProblemStatementEntity;
import com.cdg.recruit.persistence.CandidateRepository;
import com.cdg.recruit.persistence.ChallengeRepository;
import com.cdg.recruit.persistence.ProblemStatementRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final CandidateRepository candidateRepository;
    private final ChallengeRepository challengeRepository;

    public DatabaseSeeder(CandidateRepository candidateRepository, ChallengeRepository challengeRepository) {
        this.candidateRepository = candidateRepository;
        this.challengeRepository = challengeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Generate and save 10 Candidate seeds
        List<CandidateEntity> candidates = IntStream.range(0, 10)
                .mapToObj(i -> CandidateEntity.builder()
                        .cin("CIN" + i)
                        .name("Candidate " + i)
                        .email("candidate" + i + "@example.com")
                        .status("Pending")
                        .build())
                .collect(Collectors.toList());

        List<ChallengeEntity> challenges = IntStream.range(1, 11)
                .mapToObj(i -> ChallengeEntity.builder()
                        .title("Challenge " + i)
                        .difficulty(i % 3 == 0 ? "Hard" : (i % 2 == 0 ? "Medium" : "Easy"))
                        .description("Description for Challenge " + i)
                        .build())
                .collect(Collectors.toList());

        // Save Challenges
        challengeRepository.saveAll(challenges);

        // Save Candidates
        candidateRepository.saveAll(candidates);

        }
}
