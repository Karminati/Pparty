import { Injectable } from '@angular/core';
import { Comentario } from '../model/comentario';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  url: string = 'http://localhost:8080/api/v1/comentario';

  constructor(private httpClient: HttpClient) { }

  async salvar(comentario: Comentario){
    
    console.log(JSON.stringify(comentario));

    if(comentario.idComentario === 0){
     return await this.httpClient.post(this.url, JSON.stringify(comentario), this.httpHeaders).toPromise();
    }else{
      return await this.httpClient.put(this.url, JSON.stringify(comentario), this.httpHeaders).toPromise();
    }

  } 

  async buscarPorId(idComentario: number){
    let urlAux = this.url + '/' + idComentario;
    return await this.httpClient.get(urlAux).toPromise();
  }

  async buscarPorIdAvaliacao(idAvaliacao: number){
    let urlAux = this.url + '/' + idAvaliacao + '/comentario';
    return await this.httpClient.get(urlAux).toPromise();
  }

}
