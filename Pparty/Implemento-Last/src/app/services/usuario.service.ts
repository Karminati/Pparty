import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Usuario } from '../model/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  HttpHeaders = {
    headers: new HttpHeaders({ 'Content-type': 'application/json'})
  }
  url: string ='http://localhost:8080/api/v1/usuario';

  constructor(private httpClient: HttpClient) { }

  async logar(email: string, senha: string)
  {
    let url2 = this.url + "/" + email + "/" + senha + "/logar";
    return await this.httpClient.get(url2).toPromise();
  
  }

  
  async cadastrar(usuario: Usuario)
  {    
  let url2 = this.url + "/" + usuario;
  return await this.httpClient.post(this.url, usuario).toPromise();
  }
      
  async consultarPorId(id: number){
    let urlAux = this.url + "/" + id + "/usuario";
    return await this.httpClient.get(urlAux).toPromise();

  }

}
