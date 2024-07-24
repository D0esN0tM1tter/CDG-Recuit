package com.cdg.recruit.controllers;
import com.cdg.recruit.models.dtos.ChallengeDto;
import com.cdg.recruit.services.ChallengeServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ChallengeController {

    private final ChallengeServices challengeServices ;

    public ChallengeController(ChallengeServices challengeServices) {
        this.challengeServices = challengeServices;
    }

    @PostMapping("/challenges")
    public ResponseEntity<ChallengeDto> create(@RequestBody ChallengeDto challengeDto) {
        challengeServices.create(challengeDto) ;
        return new ResponseEntity<ChallengeDto>(challengeDto , HttpStatus.CREATED) ;
    }

    @GetMapping("/challenges")
    public ResponseEntity<List<ChallengeDto>> list() {
        List<ChallengeDto> result = challengeServices.findAll() ;
        return ResponseEntity.ok(result) ;
    }

    @GetMapping("/challenges/{id}")
    public ResponseEntity<ChallengeDto>  findById(@PathVariable("id") Integer id) {
        return challengeServices.findOne(id)
                .map(challengeDto -> new ResponseEntity<ChallengeDto>(challengeDto , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/challenges/random")
    public ResponseEntity<ChallengeDto> findRandomly() {
        return challengeServices.findAChallengeRandomly()
                .map(random -> new ResponseEntity<ChallengeDto>(random , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/challenges/title/{title}")
    public ResponseEntity<ChallengeDto> findByTitle(@PathVariable("title") String title) {
        return challengeServices.findByTitle(title)
                .map(challengeDto -> new ResponseEntity<ChallengeDto>(challengeDto , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/challenges/difficulty/{difficulty}")
    public ResponseEntity<List<ChallengeDto>> listByDifficulty(@PathVariable("difficulty") String difficulty ) {
        List<ChallengeDto> result = challengeServices.findByDifficulty(difficulty) ;
        return ResponseEntity.ok(result) ;
    }


    @PutMapping("/challenges/{id}")
    public ResponseEntity<ChallengeDto> update(@PathVariable("id") Integer id , @RequestBody ChallengeDto challengeDto) {
        return challengeServices.update(id , challengeDto)
                .map(challengeDto1 -> new ResponseEntity<ChallengeDto>(challengeDto1 , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;
    }

    @DeleteMapping("/challenges/{id}")
    public ResponseEntity<ChallengeDto> delete(@PathVariable("id") Integer id ) {
        return challengeServices.delete(id)
                .map(challengeDto1 -> new ResponseEntity<ChallengeDto>(challengeDto1 , HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;
    }


}
