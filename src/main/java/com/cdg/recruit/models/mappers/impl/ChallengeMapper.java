package com.cdg.recruit.models.mappers.impl;

import com.cdg.recruit.models.dtos.ChallengeDto;
import com.cdg.recruit.models.entities.ChallengeEntity;
import com.cdg.recruit.models.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class ChallengeMapper implements Mapper<ChallengeEntity , ChallengeDto> {
    private  final ModelMapper modelMapper ;

    public ChallengeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ChallengeEntity maptoEntity(ChallengeDto challengeDto) {
        return modelMapper.map(challengeDto , ChallengeEntity.class);
    }

    @Override
    public ChallengeDto mapToDto(ChallengeEntity challengeEntity) {
        return modelMapper.map(challengeEntity , ChallengeDto.class);
    }
}
