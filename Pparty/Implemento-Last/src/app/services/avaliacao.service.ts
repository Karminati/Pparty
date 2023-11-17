import { Injectable } from '@angular/core';
import { Avaliacao } from '../model/avaliacao';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AvaliacaoService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  url: string ='http://localhost:8080/api/v1/avaliacao';

  constructor(private httpClient: HttpClient) { }


  async salvar(avaliacao: Avaliacao){
    
    if(avaliacao.idAvaliacao === 0){
     return await this.httpClient.post(this.url, JSON.stringify(avaliacao), this.httpHeaders).toPromise();
    }else{
      return await this.httpClient.put(this.url, JSON.stringify(avaliacao), this.httpHeaders).toPromise();
    }

  } 

  async listar(idUsuario: number){
    let urlAuxiliar = this.url + "/" + idUsuario + "/avaliacaofesta";
    return await this.httpClient.get(urlAuxiliar).toPromise(); 
  }

  async listarTd(){
    return await this.httpClient.get(this.url).toPromise();
  }

  async buscarPorId(id: number){
    let urlAuxiliar = this.url + "/" + id; 
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }
}
