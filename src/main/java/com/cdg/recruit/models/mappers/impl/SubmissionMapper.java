package com.cdg.recruit.models.mappers.impl;

import com.cdg.recruit.models.dtos.SubmissionDto;
import com.cdg.recruit.models.entities.SubmissionEntity;
import com.cdg.recruit.models.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class SubmissionMapper implements Mapper<SubmissionEntity , SubmissionDto> {

    private final ModelMapper modelMapper ;

    public SubmissionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public SubmissionEntity maptoEntity(SubmissionDto submissionDto) {
        return modelMapper.map(submissionDto , SubmissionEntity.class);
    }

    @Override
    public SubmissionDto mapToDto(SubmissionEntity submissionEntity) {
        return modelMapper.map(submissionEntity , SubmissionDto.class);
    }
}
