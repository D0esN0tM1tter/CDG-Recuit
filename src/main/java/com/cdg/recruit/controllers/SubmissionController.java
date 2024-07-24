package com.cdg.recruit.controllers;

import com.cdg.recruit.models.dtos.SubmissionDto;
import com.cdg.recruit.services.SubmissionServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubmissionController {

    private final SubmissionServices submissionServices ;

    public SubmissionController(SubmissionServices submissionServices) {
        this.submissionServices = submissionServices;
    }

    // The creation of a submission automatically triggers its processing :

    @PostMapping("/submissions")
    public ResponseEntity <SubmissionDto> create(@RequestBody SubmissionDto submissionDto) {
        // 1 : Process the submission :
        submissionServices.processSubmission(submissionDto) ;
        // 3 : persist :
        return new ResponseEntity<SubmissionDto>(submissionDto , HttpStatus.CREATED) ;
    }

    // Find all submissions :
    @GetMapping("/submissions")
    public ResponseEntity<List<SubmissionDto>> findAll() {
        List<SubmissionDto> result =  submissionServices.findAllSubmissions() ;
        return new ResponseEntity<List<SubmissionDto>>(result , HttpStatus.OK) ;

    }


    // Find all Submissions by candidate cin :
    @GetMapping("/submissions/{cin}")
    public ResponseEntity<List<SubmissionDto>> findAllByCin(@PathVariable("cin") String cin) {
        List<SubmissionDto> result = submissionServices.findAllSubmissionsByCandidateCin(cin) ;
        return new ResponseEntity<List<SubmissionDto>>(result , HttpStatus.OK) ;
    }

}
