package com.cdg.recruit.services.impl;

import com.cdg.recruit.models.dtos.CandidateDto;
import com.cdg.recruit.models.dtos.SubmissionDto;
import com.cdg.recruit.models.entities.CandidateEntity;
import com.cdg.recruit.models.mappers.impl.CandidateMapper;
import com.cdg.recruit.persistence.CandidateRepository;
import com.cdg.recruit.services.CandidateServices;
import com.cdg.recruit.services.SubmissionServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CandidateServiceImpl implements CandidateServices {

    private static final Logger logger = LoggerFactory.getLogger(CandidateServiceImpl.class);

    private final SubmissionServices submissionServices ;
    private final CandidateRepository repository;
    private final CandidateMapper mapper;

    public CandidateServiceImpl(SubmissionServices submissionServices, CandidateRepository candidateRepository, CandidateMapper mapper) {
        this.submissionServices = submissionServices;
        this.repository = candidateRepository;
        this.mapper = mapper;
    }

    @Override
    public CandidateDto create(CandidateDto dto) {
        // Map the dto to an entity
        CandidateEntity candidateEntity = mapper.maptoEntity(dto);

        // Insert the entity
        repository.save(candidateEntity);

        logger.info("Created candidate with CIN: {}", dto.getCin());

        return dto;
    }

    @Override
    public Optional<CandidateDto> findOne(String id) {
        Optional<CandidateDto> candidate = repository.findById(id).map(mapper::mapToDto);
        if (candidate.isPresent()) {
            logger.info("Found candidate with CIN: {}", id);
        } else {
            logger.warn("Candidate with CIN: {} not found", id);
        }
        return candidate;
    }

    @Override
    public List<CandidateDto> findAll() {
        // Extract all candidates
        List<CandidateEntity> candidateEntities = StreamSupport.stream(repository.findAll().spliterator(), false).toList();

        // Map all instances to dtos
        List<CandidateDto> candidates = candidateEntities.stream().map(mapper::mapToDto).toList();
        logger.info("Found {} candidates", candidates.size());

        return candidates;
    }

    @Override
    public Optional<CandidateDto> update(String cin, CandidateDto candidate) {
        Optional<CandidateDto> result = findOne(cin);

        if (result.isEmpty()) {
            logger.warn("Cannot update candidate. Candidate with CIN: {} not found", cin);
            return Optional.empty();
        }

        candidate.setCin(cin);
        create(candidate);

        logger.info("Updated candidate with CIN: {}", cin);

        return Optional.of(candidate);
    }

    @Override
    public Optional<CandidateDto> delete(String cin) {
        // Check whether the target exists or not
        Optional<CandidateDto> result = findOne(cin);
        if (result.isEmpty()) {
            logger.warn("Cannot delete candidate. Candidate with CIN: {} not found", cin);
            return Optional.empty();
        }

        try {
            // Delete from the database
            repository.deleteById(cin);
            logger.info("Deleted candidate with CIN: {}", cin);
        } catch (Exception e) {
            logger.error("Error deleting candidate with CIN: {}", cin, e);
            return Optional.empty();
        }

        return result;
    }

    @Override
    public CandidateDto validateCandidate(String cin) {
        return null;
    }

//    @Override
//    public CandidateDto validateCandidate(String cin) {
//        String candidateStatus = "Succeeded" ;
//
//        // List all the submissions for the current candidate ;
//        List<SubmissionDto> submissions = submissionServices.findAllSubmissions() ;
//
//        for (SubmissionDto submissionDto : submissions) {
//
//        }
//        // Check if at least 2 is correct
//        // Validate
//    }
}
