package com.cdg.recruit.persistence;

import com.cdg.recruit.models.entities.ProblemStatementEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemStatementRepository extends CrudRepository<ProblemStatementEntity, Integer> {

    @Query("SELECT p FROM ProblemStatementEntity p WHERE p.language =:language AND p.challenge.title =:challenge_title")
    public Optional<ProblemStatementEntity> findByChallengeTitleAndLanguage(@Param("challenge_title") String challengeTitle, @Param("language") String language);

    @Query("SELECT p FROM ProblemStatementEntity p WHERE p.challenge.title =:title")
    public List<ProblemStatementEntity> findAllProblemStatementsByChallengeTitle(@Param("title") String challengeTitle);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProblemStatementEntity p WHERE p.challenge.title =:challenge_title AND p.language =:language")
    public Optional<ProblemStatementEntity> deleteByTitleAndLanguage(@Param("challenge_title") String challengeTitle, @Param("language") String language);
}
