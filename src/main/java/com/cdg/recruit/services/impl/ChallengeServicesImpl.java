package com.cdg.recruit.services.impl;

import com.cdg.recruit.models.dtos.ChallengeDto;
import com.cdg.recruit.models.entities.ChallengeEntity;
import com.cdg.recruit.models.mappers.impl.ChallengeMapper;
import com.cdg.recruit.persistence.ChallengeRepository;
import com.cdg.recruit.services.ChallengeServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class ChallengeServicesImpl implements ChallengeServices {

    private final ChallengeRepository challengeRepository;
    private final ChallengeMapper mapper;

    public ChallengeServicesImpl(ChallengeRepository challengeRepository, ChallengeMapper mapper) {
        this.challengeRepository = challengeRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<ChallengeDto> findByTitle(String title) {
        return challengeRepository.findByTitle(title).map(mapper::mapToDto);
    }

    @Override
    public List<ChallengeDto> findByDifficulty(String difficulty) {
        List<ChallengeEntity> result = challengeRepository.findByDifficulty(difficulty).stream().toList() ;

        return result.stream().map(mapper::mapToDto).toList();

    }

    @Override
    public Optional<ChallengeDto> findAChallengeRandomly() {
        return challengeRepository.findARandomChallenge().map(mapper::mapToDto);
    }

    @Override
    public ChallengeDto create(ChallengeDto challengeDto) {
        // Map the dto to an entity :
        ChallengeEntity challengeEntity = mapper.maptoEntity(challengeDto);
        // Insert to the database :
        challengeRepository.save(challengeEntity);
        // Return :
        return challengeDto;

    }

    @Override
    public Optional<ChallengeDto> findOne(Integer id) {
        return challengeRepository.findById(id).map(mapper::mapToDto);
    }

    @Override
    public List<ChallengeDto> findAll() {
        List<ChallengeEntity> result = StreamSupport.stream(
                challengeRepository.findAll().spliterator(), false).toList();

        return result.stream().map(mapper::mapToDto).toList();
    }

    @Override
    public Optional<ChallengeDto> update(Integer id, ChallengeDto challengeDto) {
        challengeDto.setId(id);
        challengeRepository.save(mapper.maptoEntity(challengeDto));
        return Optional.of(challengeDto);
    }

    @Override
    public Optional<ChallengeDto> delete(Integer id) {
        // Check weather the target entity exist :
        Optional<ChallengeEntity> result = challengeRepository.findById(id);

        if (result.isEmpty()) {
            return Optional.empty();
        }

        challengeRepository.deleteById(id);
        // Map to dto :
        ChallengeDto challengeDto = mapper.mapToDto(result.get()) ;
        return Optional.of(challengeDto) ;

    }


}
