package com.arabsoft.pfe.projet.model.forfaitprep;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="forfait")
@Data
@NoArgsConstructor
public class Forfait {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String typeForfait;
    private Long prix;

    @ManyToMany(mappedBy = "forfaits")
    private Set<Client> clients = new HashSet<>();
    
}
