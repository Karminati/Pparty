export class Usuario 
{

    idUsuario: number;
    email: string;
    nome: string;
    senha: string;
    datanasc: String;
    endereco: String;

    constructor()
    {
        this.idUsuario = 0;
        this.email = "";
        this.nome = "";
        this.senha = "";
        this.datanasc = "";
        this.endereco = "";
    }
}
