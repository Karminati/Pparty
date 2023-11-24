import { Injectable } from '@angular/core';
import { Chat } from '../model/chat';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  url = 'http://localhost:8080/api/v1/chat'

  constructor(private httpClient: HttpClient) { }

  async salvar(chat: Chat){
    return await this.httpClient.post(this.url, JSON.stringify(chat), this.httpHeaders).toPromise();
  }

  async buscarChatPorId(id: number){
    let urlAux = this.url + "/" + id;
    return await this.httpClient.get(urlAux).toPromise();
  }

  async buscarChatPorIdUsuario(idUsuario: number){
    let urlAux = this.url + "/" + idUsuario + "/usuario";
    return await this.httpClient.get(urlAux).toPromise();
  }

}
