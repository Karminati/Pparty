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


public class Cliente {
    private int id;
    private int idade; 
    private String nome;
    private String email;
    private String senha;


}
