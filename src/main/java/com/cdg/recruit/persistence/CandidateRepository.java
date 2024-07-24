package com.cdg.recruit.persistence;

import com.cdg.recruit.models.entities.CandidateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends CrudRepository<CandidateEntity , String> {

}
