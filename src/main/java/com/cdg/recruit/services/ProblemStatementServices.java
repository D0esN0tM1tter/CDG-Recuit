package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.ProblemStatementDto;

import java.util.Optional;

public interface ProblemStatementServices  {

    public ProblemStatementDto create(ProblemStatementDto problemStatementDto) ;
    public Optional<ProblemStatementDto> findById(Integer id ) ;
    public Optional<ProblemStatementDto> findByChallengeTitleAndLanguage(String challengeTitle , String  language) ;
    public Optional<ProblemStatementDto> updateByChalllengeTitleAndLanguage(String challengeTitle , String language  , ProblemStatementDto problemStatementDto) ;
    public Optional<ProblemStatementDto> updateStatementAndExpectedOutput(String challengeTitle , String language , String statement , String expected_output ) ;
    public Optional<ProblemStatementDto> deleteByTitleAndLanguage(String challengeId , String language) ;
}
