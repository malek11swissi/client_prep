package com.arabsoft.pfe.projet.model.forfaitprep;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="pack")
@Data
@NoArgsConstructor
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
}
