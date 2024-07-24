package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.CandidateDto;

import java.util.List;
import java.util.Optional;

public interface CandidateServices extends Services<CandidateDto , String> {

    public CandidateDto validateCandidate (String cin ) ;
}
