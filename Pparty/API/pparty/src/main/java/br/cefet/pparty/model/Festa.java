package br.cefet.pparty.model;

import java.util.Date;
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

public class Festa {
    private int idFesta;
    private Date data;
    private String titulo;
    private String enderecofesta;
    private double valorIngresso;
    private String cardapio;
    private int qingresso;
    private String categoria;
    private String latitude;
    private String longitude;
    private int idUsuariof;
}
