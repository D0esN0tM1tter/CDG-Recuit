package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.ChallengeDto;

import java.util.List;
import java.util.Optional;

public interface ChallengeServices extends Services<ChallengeDto , Integer> {
    public Optional<ChallengeDto> findByTitle(String title) ;
    public List<ChallengeDto> findByDifficulty(String difficulty) ;
    public Optional<ChallengeDto> findAChallengeRandomly() ;
}
