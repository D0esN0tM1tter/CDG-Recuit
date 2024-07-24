package com.cdg.recruit.models.mappers.impl;

import com.cdg.recruit.models.dtos.ProblemStatementDto;
import com.cdg.recruit.models.entities.ProblemStatementEntity;
import com.cdg.recruit.models.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProblemStatemetMapper implements Mapper<ProblemStatementEntity , ProblemStatementDto> {
    private final ModelMapper modelMapper ;

    public ProblemStatemetMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProblemStatementEntity maptoEntity(ProblemStatementDto problemStatementDto) {
        return modelMapper.map(problemStatementDto , ProblemStatementEntity.class);
    }

    @Override
    public ProblemStatementDto mapToDto(ProblemStatementEntity problemStatementEntity) {
        return modelMapper.map(problemStatementEntity , ProblemStatementDto.class);
    }
}
