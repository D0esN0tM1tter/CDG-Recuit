package com.cdg.recruit.controllers;

import com.cdg.recruit.models.dtos.CandidateDto;
import com.cdg.recruit.services.CandidateServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CandidateController {

    private final CandidateServices candidateServices ;

    public CandidateController(CandidateServices candidateServices) {
        this.candidateServices = candidateServices;
    }

    // Creating a candidate :
    // On paper the candidate fields are going to be the input of a form
   @PostMapping(path = "/candidates")
    public ResponseEntity<CandidateDto> create(@RequestBody CandidateDto candidateDto) {
        candidateServices.create(candidateDto) ;
        return new ResponseEntity<CandidateDto>(candidateDto , HttpStatus.CREATED) ;
   }

   @GetMapping("/candidates")
    public ResponseEntity<List<CandidateDto>> list() {
        List<CandidateDto> candidateDtoList = candidateServices.findAll() ;
        return new ResponseEntity<List<CandidateDto>>(candidateDtoList , HttpStatus.OK) ;
   }

   @GetMapping("/candidates/{cin}")
    public ResponseEntity<CandidateDto> findOne(@PathVariable("cin") String cin ) {
        return candidateServices.findOne(cin)
                .map(candidateDto -> new ResponseEntity<CandidateDto>(candidateDto , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;
   }

   @PutMapping("/candidates/{cin}")
    public ResponseEntity<CandidateDto> update(@PathVariable("cin") String cin , @RequestBody CandidateDto candidateDto) {
        return candidateServices.update(cin , candidateDto)
                .map(candidateDto1 -> new ResponseEntity<CandidateDto>(candidateDto1 , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;

   }

   @DeleteMapping("/candidates/{cin}")
    public ResponseEntity<CandidateDto> delete(@PathVariable("cin") String cin) {
        return candidateServices.delete(cin)
                .map(candidateDto -> new ResponseEntity<CandidateDto>(candidateDto , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;
    }

}
