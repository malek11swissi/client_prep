package com.arabsoft.pfe.projet.model.framework;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ObjetRetour {
    public ObjetRetour(Object data)  {
        this.data =  data;
    }

    public ObjetRetour(String code, String messageException) {
        this.code = code;
        this.messageException = messageException;
    }

    private String code;
    private String messageException;
    private Object data;
}
