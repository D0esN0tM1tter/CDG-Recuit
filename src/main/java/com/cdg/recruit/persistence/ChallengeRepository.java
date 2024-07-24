package com.cdg.recruit.persistence;

import com.cdg.recruit.models.entities.ChallengeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends CrudRepository<ChallengeEntity , Integer> {

    public List<ChallengeEntity> findByDifficulty(String difficulty) ;

    public Optional<ChallengeEntity> findByTitle(String title) ;

    @Query(value = "SELECT * FROM Challenges ORDER BY RAND() LIMIT 1" , nativeQuery = true)
    public Optional<ChallengeEntity> findARandomChallenge() ;

}
