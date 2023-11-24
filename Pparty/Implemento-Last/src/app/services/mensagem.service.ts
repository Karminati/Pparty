import { Injectable } from '@angular/core';
import { Mensagem } from '../model/mensagem';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MensagemService {
  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  url = 'http://localhost:8080/api/v1/mensagem'

  constructor(private httpClient: HttpClient) { }

  async salvarMsg(mensagem: Mensagem){
    return await this.httpClient.post(this.url, JSON.stringify(mensagem), this.httpHeaders).toPromise();
  }

  async consultarPorIdChat(idChat: number){
    let urlAux = this.url + "/" + idChat;
    return await this.httpClient.get(urlAux).toPromise();
  }

}
