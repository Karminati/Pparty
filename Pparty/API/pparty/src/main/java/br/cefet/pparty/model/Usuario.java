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


public class Usuario {
    private int idUsuario;
    private Date datanasc;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    
}
