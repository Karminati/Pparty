import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../../services/usuario.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ToastController, NavController, AlertController, LoadingController } from '@ionic/angular';
import { Usuario } from '../../model/usuario';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.page.html',
  styleUrls: ['./perfil.page.scss'],
})
export class PerfilPage implements OnInit {

  formGroup: FormGroup;
  nome: String;
  email: String;
  usuario: Usuario;
  datanasc: String;
  endereco: String;
  senha: String;
  data: String;
  datas: String;

  constructor(private activatedRoute: ActivatedRoute, private usuarioService: UsuarioService, private fBuilder: FormBuilder, private toastController: ToastController, private navController: NavController, private alertController: AlertController, private loadingController: LoadingController) {
    this.nome = "";
    this.email = "";
    this.senha = "";
    this.datanasc = "";
    this.endereco = "";
    this.usuario = new Usuario();
    this.data = "";

    this.formGroup = this.fBuilder.group(
      {
        'nome': [{ value: this.nome, disabled: true }],
        'email': [{ value: this.email, disabled: true }],
        'senha': [{ value: this.senha, disabled: true }],
        'datanasc': [{ value: this.datanasc, disabled: true }],
        'endereco': [{ value: this.endereco, disabled: true }],
      }

    )
     this.usuario = JSON.parse(localStorage.getItem('usuario') || '[]');
      this.formGroup.get('nome')?.setValue(this.usuario.nome);
      this.formGroup.get('email')?.setValue(this.usuario.email);
      this.formGroup.get('senha')?.setValue(this.usuario.senha);
      this.formGroup.get('endereco')?.setValue(this.usuario.endereco);
      this.datas = this.usuario.datanasc;
      this.datas =this.datas.slice(0, 10);
      this.formGroup.get('datanasc')?.setValue( this.datas) ;

  }

  ngOnInit() {
  }
  
  Sair()
  {
    localStorage.clear();
    this.exibirMensagem("Saindo da Conta");
    this.navController.navigateBack('/login');
  }
  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }

  async Festas()
  {
    this.usuario.idUsuario;
    window.location.href = "http://localhost:8080/api/v1/report/festa/" +this.usuario.idUsuario +"/usuario";
  }
} 
