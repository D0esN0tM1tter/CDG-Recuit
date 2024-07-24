package com.cdg.recruit.services.impl;


import com.cdg.recruit.models.dtos.ProblemStatementDto;
import com.cdg.recruit.models.mappers.impl.ProblemStatemetMapper;
import com.cdg.recruit.persistence.ProblemStatementRepository;
import com.cdg.recruit.services.ProblemStatementServices;
import org.springframework.stereotype.Service;
import java.util.Optional;



@Service
public class ProblemStatementServicesImpl implements ProblemStatementServices {

    private final ProblemStatementRepository problemStatementRepository ;
    private final ProblemStatemetMapper problemStatemetMapper ;

    public ProblemStatementServicesImpl(ProblemStatementRepository problemStatementRepository, ProblemStatemetMapper problemStatemetMapper) {
        this.problemStatementRepository = problemStatementRepository;
        this.problemStatemetMapper = problemStatemetMapper;
    }


    @Override
    public ProblemStatementDto create(ProblemStatementDto problemStatementDto) {
        problemStatementRepository.save(problemStatemetMapper.maptoEntity(problemStatementDto)) ;
        return problemStatementDto ;
    }

    @Override
    public Optional<ProblemStatementDto> findById(Integer id) {
        return problemStatementRepository.findById(id).map(problemStatemetMapper::mapToDto) ;
    }

    @Override
    public Optional<ProblemStatementDto> findByChallengeTitleAndLanguage(String challengeTitle, String language) {
        return problemStatementRepository.findByChallengeTitleAndLanguage(challengeTitle , language).map(problemStatemetMapper::mapToDto) ;
    }

    @Override
    public Optional<ProblemStatementDto> updateByChalllengeTitleAndLanguage(String challengeTitle, String language , ProblemStatementDto  problemStatementDto) {
            // First : find and extract the target record's id :
        Optional<ProblemStatementDto> result = findByChallengeTitleAndLanguage(challengeTitle , language) ;

        if (result.isPresent()) {
            problemStatementDto.setId(result.get().getId());
            // Save :
            return Optional.of(create(problemStatementDto)) ;
        }
        return Optional.empty();
    }

    @Override
    public Optional<ProblemStatementDto> updateStatementAndExpectedOutput(String challengeTitle, String language, String statement, String expected_output) {
        // Retrieve the target :
        Optional<ProblemStatementDto> result = findByChallengeTitleAndLanguage(challengeTitle , language) ;

        if (result.isPresent()) {
            ProblemStatementDto problemStatementDto = result.get() ;
            problemStatementDto.setStatement(statement) ;
            problemStatementDto.setExpected_output(expected_output);

            return Optional.of(create(problemStatementDto)) ;
        }
        return Optional.empty() ;
    }


    @Override
    public Optional<ProblemStatementDto> deleteByTitleAndLanguage(String challengeTitle , String language) {
        // Retrieve the target :
        Optional<ProblemStatementDto> result = findByChallengeTitleAndLanguage(challengeTitle , language) ;

        if (result.isPresent()) {
            problemStatementRepository.deleteByTitleAndLanguage(challengeTitle , language) ;
            return result ;
        }

        return Optional.empty() ;
    }


}
