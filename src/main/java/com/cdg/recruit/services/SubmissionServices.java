package com.cdg.recruit.services;

import com.cdg.recruit.models.dtos.SubmissionDto;

import java.util.List;
import java.util.Optional;

public interface SubmissionServices {

    public SubmissionDto create(SubmissionDto submissionDto) ;
    public SubmissionDto processSubmission(SubmissionDto submissionDto);
    public List<SubmissionDto>  findAllSubmissions() ;
    public List<SubmissionDto> findAllSubmissionsByCandidateCin(String cin) ;
    public Optional<SubmissionDto> delete(Integer submissionId) ;
    public Optional<SubmissionDto> findById(Integer submissionId) ;
}
