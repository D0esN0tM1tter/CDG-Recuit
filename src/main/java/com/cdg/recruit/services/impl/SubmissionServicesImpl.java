package com.cdg.recruit.services.impl;

import com.cdg.recruit.models.dtos.JDoodleResponse;
import com.cdg.recruit.models.dtos.ProblemStatementDto;
import com.cdg.recruit.models.dtos.SubmissionDto;
import com.cdg.recruit.models.entities.SubmissionEntity;
import com.cdg.recruit.models.mappers.impl.SubmissionMapper;
import com.cdg.recruit.persistence.SubmissionRepository;
import com.cdg.recruit.services.JDoodleServices;
import com.cdg.recruit.services.ProblemStatementServices;
import com.cdg.recruit.services.SubmissionServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class SubmissionServicesImpl implements SubmissionServices {


    private final SubmissionRepository submissionRepository ;
    private final JDoodleServices jDoodleServices ;
    private final ProblemStatementServices problemStatementServices ;
    private final SubmissionMapper mapper ;

    public SubmissionServicesImpl(SubmissionRepository submissionRepository , JDoodleServices jDoodleServices, ProblemStatementServices problemStatementServices, SubmissionMapper mapper) {
        this.submissionRepository = submissionRepository;
        this.jDoodleServices = jDoodleServices;
        this.problemStatementServices = problemStatementServices;
        this.mapper = mapper;
    }

    @Override
    public SubmissionDto create(SubmissionDto submissionDto) {
        submissionRepository.save(mapper.maptoEntity(submissionDto)) ;
        return submissionDto ;
    }
    @Override
    public SubmissionDto processSubmission(SubmissionDto submissionDto) {
        // Extract code and problem details
        String code = submissionDto.getCodeContent();
        ProblemStatementDto problem = submissionDto.getProblem();
        String language = problem.getLanguage();
        String expectedOutput = problem.getExpected_output();

        // Process the submission with JDoodle
        JDoodleResponse jDoodleResponse = jDoodleServices.processSubmission(code, language);

        // Calculate the correctness score
        double correctnessScore = jDoodleResponse.getOutput().trim().equalsIgnoreCase(expectedOutput.trim()) ? 100 : 0.0;

        // For now, only use correctness as the score
        double score = correctnessScore;

        // Set the score and output in the submissionDto
        submissionDto.setScore(score);
        submissionDto.setOutput(jDoodleResponse.getOutput());

        // Save the submission entity
        SubmissionEntity submissionEntity = mapper.maptoEntity(submissionDto);
        submissionRepository.save(submissionEntity);

        return mapper.mapToDto(submissionEntity);
    }





    @Override
    public List<SubmissionDto> findAllSubmissions() {
        List<SubmissionEntity> result = StreamSupport.stream(
                submissionRepository.findAll().spliterator() , false
        ).toList() ;

        return result.stream().map(mapper::mapToDto).toList() ;
    }

    @Override
    public List<SubmissionDto> findAllSubmissionsByCandidateCin(String cin) {
        List<SubmissionEntity> result = submissionRepository.findAllSubmissionsByCandidateCin(cin).stream().toList() ;
        return result.stream().map(mapper::mapToDto).toList() ;
    }

    @Override
    public Optional<SubmissionDto> delete(Integer submissionId) {
        // Retrieve the target record :
        Optional<SubmissionDto> result = findById(submissionId) ;

        if (result.isPresent()) {
            submissionRepository.deleteById(submissionId);
            return result ;
        }

        return Optional.empty() ;
    }

    @Override
    public Optional<SubmissionDto> findById(Integer submissionId) {
        return submissionRepository.findById(submissionId).map(mapper::mapToDto) ;
    }


}
