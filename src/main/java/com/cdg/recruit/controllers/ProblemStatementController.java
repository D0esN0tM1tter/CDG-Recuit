package com.cdg.recruit.controllers;
import com.cdg.recruit.models.dtos.ProblemStatementDto;
import com.cdg.recruit.services.ProblemStatementServices;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProblemStatementController {

    private final ProblemStatementServices problemStatementServices ;

    public ProblemStatementController(ProblemStatementServices problemStatementServices) {
        this.problemStatementServices = problemStatementServices;
    }

    @PostMapping ("/problems")
    public ResponseEntity<ProblemStatementDto> create(@RequestBody ProblemStatementDto problemStatementDto) {
        problemStatementServices.create(problemStatementDto) ;
        return new ResponseEntity<ProblemStatementDto>(problemStatementDto , HttpStatus.CREATED) ;
    }

    @GetMapping("/problems/{challenge_title}/{language}")
    public ResponseEntity<ProblemStatementDto> findOne(@PathVariable("challenge_title") String challengeTitle, @PathVariable("language") String language) {
        return problemStatementServices.findByChallengeTitleAndLanguage(challengeTitle, language)
                .map(problemStatementDto -> new ResponseEntity<>(problemStatementDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/problems/{challenge_title}/{language}")
    public ResponseEntity<ProblemStatementDto> update(@PathVariable("challenge_title") String challengeTitle, @PathVariable("language") String language, @RequestBody ProblemStatementDto problemStatementDto) {
        return problemStatementServices.updateByChalllengeTitleAndLanguage(challengeTitle, language, problemStatementDto)
                .map(updatedDto -> new ResponseEntity<>(updatedDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PatchMapping("/problems/{challenge_title}/{language}/{statement}/{expected_output}")
    public ResponseEntity<ProblemStatementDto> partialUpdate (@PathVariable("challenge_title") String challengetitle , @PathVariable("language") String language, @PathVariable("statement") String statement , @PathVariable("expected_output") String expected_output) {
        return problemStatementServices.updateStatementAndExpectedOutput(challengetitle , language , statement  , expected_output)
                .map(problemStatementDto1 -> new ResponseEntity<ProblemStatementDto>(problemStatementDto1 , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;
    }

    @DeleteMapping("/problems/{challenge_title}/{language}")
    public ResponseEntity<ProblemStatementDto> update(@PathVariable("challenge_title") String challengetitle , @PathVariable("language") String language) {
        return problemStatementServices.deleteByTitleAndLanguage(challengetitle , language )
                .map(problemStatementDto1 -> new ResponseEntity<ProblemStatementDto>(problemStatementDto1 , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;
    }


}
