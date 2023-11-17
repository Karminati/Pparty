
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

public class Avaliacao {
    private int idAvaliacao;
    private int quantAvaliacao;
    private int idUsuarioa;
    private int tbFesta_idFesta;
}
