package com.cdg.recruit.models.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "candidates")

public class CandidateEntity {
    @Id
    private String cin ;
    private String name ;
    private String email ;
    private String status ;



}
