import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastController, NavController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.page.html',
  styleUrls: ['./cadastro.page.scss'],
})
export class CadastroPage implements OnInit {

  usuario: Usuario;

  formGroup: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private toastController:
     ToastController, private navController: NavController, private formBuilder: 
     FormBuilder, private usuarioService: UsuarioService) 
  {
    this.usuario = new Usuario();

    this.formGroup = this.formBuilder.group(
      {
        'email': ["", Validators.compose([
          Validators.required, Validators.email
        ])],
        'senha': ["", Validators.compose([
          Validators.required
        ])],
        'nome': ["", Validators.compose([
          Validators.required
        ])],
        'endereco': ["", Validators.compose([
          Validators.required
        ])],
        'datanasc': ["", Validators.compose([
          Validators.required
        ])],
      }
    )
    let id = this.activatedRoute.snapshot.params['id'];
  }
  
  ngOnInit() {
  }

  
  cadastrar() 
  {

   
    this.usuario.email = this.formGroup.value.email
    this.usuario.nome = this.formGroup.value.nome
    this.usuario.senha = this.formGroup.value.senha
    this.usuario.datanasc = this.formGroup.value.datanasc
    this.usuario.endereco = this.formGroup.value.endereco
    console.log("Usuario"+this.usuario);
    this.usuarioService.cadastrar(this.usuario).then((json)=>
    {
      this.usuario = <Usuario>(json);
      console.log(this.usuario.idUsuario)
      if (this.usuario) 
      {
        localStorage.setItem('usuario', JSON.stringify(this.usuario));
        this.exibirMensagem('Bem vindo!!!');
        this.navController.navigateBack('/inicio');
      } else {
        this.exibirMensagem('Este usuario não existe ou senha incorreta.');
        this.formGroup.value.email = null;
        this.formGroup.value.senha = null;
      }
    }).catch((erro) =>
    {
      this.exibirMensagem('Erro ao logar! Erro: ' + erro['mensage']);
    });
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }

}
