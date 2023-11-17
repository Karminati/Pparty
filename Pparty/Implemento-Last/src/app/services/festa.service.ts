import { Injectable } from '@angular/core';
import { Festa } from '../model/festa';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class FestaService {

  httpHeaders = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  }

  url: string = 'http://localhost:8080/api/v1/festa';

  constructor(private httpClient: HttpClient) { }

  async salvar(festa: Festa){
    
    if(festa.idFesta === 0){
     return await this.httpClient.post(this.url, JSON.stringify(festa), this.httpHeaders).toPromise();
    }else{
      return await this.httpClient.put(this.url, JSON.stringify(festa), this.httpHeaders).toPromise();
    }
  } 

  async listar(idUsuario: number){
    let urlAuxiliar = this.url + "/" + idUsuario + "/festa";
    return await this.httpClient.get(urlAuxiliar).toPromise(); 
  }

  async listarTd(){
    return await this.httpClient.get(this.url).toPromise(); 
  }

  async buscarPorId(id: number){
    let urlAuxiliar = this.url + "/" + id; 
    return await this.httpClient.get(urlAuxiliar).toPromise();
  }

  async excluir(id: number){
      let urlAuxiliar = this.url + "/" + id;
      return await this.httpClient.delete(urlAuxiliar).toPromise();
  }    

  async verificarNum(id: number, numConta: string){
    let urlAuxiliar = this.url + "/" + id + "/" + numConta + "/numero/exists";
    return await this.httpClient.get(urlAuxiliar).toPromise();
    
  }

  async filtrarFesta(pesquisa: String){

    // let pesquisaAux = pesquisa.split(" ");

    let pesquisaAux = pesquisa.replace(/\s+/g, '%20');

    console.log(pesquisaAux);

    let urlAuxiliar = this.url + "/pesquisa?pesquisa=" + pesquisaAux;

    return await this.httpClient.get(urlAuxiliar).toPromise();
  }

}
