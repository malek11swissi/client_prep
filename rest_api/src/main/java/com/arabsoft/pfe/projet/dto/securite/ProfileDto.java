package com.arabsoft.pfe.projet.dto.securite;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
    private Long id;
    private String code;
    private String libelle;
    private boolean actif;
}
