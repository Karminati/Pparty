package br.cefet.pparty.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class FotoFesta {
    private int idFoto;
    private String url;
    private int fkFotoFesta;
}
