package com.cdg.recruit.persistence;
import com.cdg.recruit.models.entities.SubmissionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubmissionRepository extends CrudRepository<SubmissionEntity , Integer> {

    @Query("SELECT s FROM SubmissionEntity s WHERE s.candidate.cin =:cin")
    public List<SubmissionEntity> findAllSubmissionsByCandidateCin(@Param("cin") String cin) ;


}
