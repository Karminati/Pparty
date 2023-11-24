export class Mensagem {
    idMensagem: number;
    conteudo: string;
    tbChat_IdChat: number;
    udOrigem: number;

    constructor(){
        this.idMensagem = 0;
        this.conteudo = "";
        this.tbChat_IdChat = 0;
        this.udOrigem = 0;
    }
}
