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


public class Chat {
    private int idChat;
    private int idUsuario1;
    private int idUsuario2;

}
