package com.cdg.recruit.models.mappers.impl;

import com.cdg.recruit.models.dtos.CandidateDto;
import com.cdg.recruit.models.entities.CandidateEntity;
import com.cdg.recruit.models.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper implements Mapper<CandidateEntity , CandidateDto> {

    private  final ModelMapper modelMapper ;

    public CandidateMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CandidateEntity maptoEntity(CandidateDto candidateDto) {
        return modelMapper.map(candidateDto , CandidateEntity.class) ;
    }

    @Override
    public CandidateDto mapToDto(CandidateEntity candidateEntity) {
        return modelMapper.map(candidateEntity , CandidateDto.class);
    }
}
