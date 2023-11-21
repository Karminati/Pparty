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

public class Mensagem {
    private int idMensagem;
    private String conteudo;
    private int tbChat_IdChat;
    private int udOrigem;

}
