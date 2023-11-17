import { Component, OnInit } from '@angular/core';
import { FestaService } from 'src/app/services/festa.service';
import { ActivatedRoute } from '@angular/router';
import { ToastController } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { getLocaleDateFormat } from '@angular/common';
import { Festa } from 'src/app/model/festa';
import { LoadingController } from '@ionic/angular';
import { AlertController } from '@ionic/angular';
import { Avaliacao } from 'src/app/model/avaliacao';
import { AvaliacaoService } from 'src/app/services/avaliacao.service';
import { Usuario } from 'src/app/model/usuario';
import { Comentario } from 'src/app/model/comentario';
import { ComentarioService } from 'src/app/services/comentario.service';


@Component({
  selector: 'app-visualizar-festa',
  templateUrl: './visualizar-festa.page.html',
  styleUrls: ['./visualizar-festa.page.scss'],
})
export class VisualizarFestaPage implements OnInit {

  idFesta: number;
  festa2: Festa;
  formGroup: FormGroup;
  avaliacao: Avaliacao;
  usuario: Usuario;
  comentario: Comentario;
  avaliacoes: Avaliacao[];
  // ratingMedio: number;

  constructor(private alertController: AlertController, private loadingController: LoadingController, private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private festaService: FestaService, private avaliacaoService: AvaliacaoService, private comentarioService: ComentarioService) { 
    this.festa2 = new Festa();
    this.avaliacao = new Avaliacao();
    this.comentario = new Comentario();
    this.avaliacoes = [];


    let id = this.activatedRoute.snapshot.params['id'];
    this.idFesta = id;

    // this.avaliacaoService.listarTd()
    // .then((json) => {
    //   this.avaliacoes = <Avaliacao[]> (json);
    // });

    // this.ratingMedio = 0;
    // let j = 0;
    // for(let i = 0; i <= this.avaliacoes.length; i++){
    //   console.log(this.avaliacoes[i].tbFesta_idFesta);
    //   console.log(this.idFesta);
    //   if(this.avaliacoes[i].tbFesta_idFesta === this.idFesta){
    //     this.ratingMedio = this.ratingMedio + this.avaliacoes[i].quantAvaliacao;
    //     console.log(this.avaliacoes[i].quantAvaliacao)
    //     j++;
    //   }
    // }

    // this.ratingMedio = this.ratingMedio/j;

    this.usuario = JSON.parse(localStorage.getItem('usuario') || '[]');

    this.formGroup = this.formBuilder.group(
      {
        'titulo': [this.festa2.titulo, Validators.compose([
          Validators.required
        ])],
        'endereco': [this.festa2.enderecofesta, Validators.compose([
          Validators.required
        ])],
        'data': [this.festa2.data, Validators.compose([
          Validators.required
        ])],
        'valorIngresso': [this.festa2.valorIngresso, Validators.compose([
          Validators.required
        ])],
        'cardapio': [this.festa2.cardapio, Validators.compose([
          Validators.required
        ])],
        'qIngresso': [this.festa2.qingresso, Validators.compose([
          Validators.required
        ])],
        'categoria': [this.festa2.categoria, Validators.compose([
          Validators.required
        ])],
        'latitude': [this.festa2.latitude, Validators.compose([
        ])],
        'longitude': [this.festa2.longitude, Validators.compose([
        ])],
      }
    )

    console.log(window.location.href);




    if (id != null) {
      this.festaService.buscarPorId(parseInt(id))
      .then((json) => {
        this.festa2 = <Festa>(json);
        this.formGroup.get('titulo')?.setValue(this.festa2.titulo);
        this.formGroup.get('endereco')?.setValue(this.festa2.enderecofesta);
        this.formGroup.get('data')?.setValue(this.festa2.data);
        this.formGroup.get('valorIngresso')?.setValue(this.festa2.valorIngresso);
        this.formGroup.get('cardapio')?.setValue(this.festa2.cardapio);
        this.formGroup.get('qIngresso')?.setValue(this.festa2.qingresso);
        this.formGroup.get('categoria')?.setValue(this.festa2.categoria);
        this.formGroup.get('latitude')?.setValue(this.festa2.latitude);
        this.formGroup.get('longitude')?.setValue(this.festa2.longitude);
      })
      .catch((erro) => {
        this.exibirMensagem('Erro ao recuperar o registro! Erro:  ' + erro['message']);
      })
    }


  }

  ngOnInit() {
  }


  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }

  exibirLoader(){
    this.loadingController.create({
      message: 'Carregando...'
    }).then((res)=>{
      res.present();
    })
  }

  fecharLoader(){
    setTimeout(()=>{
      this.loadingController.dismiss().then(()=>{
      }).catch((erro)=>{
        console.log('Erro:  ', erro)
      });
    }, 500);
  }

  async salvar(){
    this.avaliacao.idUsuarioa = this.usuario.idUsuario;
    this.avaliacao.tbFesta_idFesta = this.idFesta;

    this.avaliacaoService.salvar(this.avaliacao)
    .then((json) => {
      this.avaliacao = <Avaliacao>(json);
      if (this.avaliacao) {
        this.exibirMensagem('Adicionada a Festas Salvas');
        console.log(this.avaliacao.idAvaliacao);

        this.comentario.idAvaliacaoc = this.avaliacao.idAvaliacao;
        this.comentario.coment = "";
        this.comentarioService.salvar(this.comentario)
        .then((json) => {
          this.comentario = <Comentario>(json);
          if (this.comentario) {
            this.exibirMensagem('Registro salvo');
          } else {
            this.exibirMensagem('Erro ao salvar o registro');
          }
        })
        .catch((erro) => {
          this.exibirMensagem('Erro ao salvar o registro! Erro:  ' + erro['message']);
        });
        

        this.navController.navigateBack('/inicio');
      } else {
        this.exibirMensagem('Erro ao salvar o registro');
      }


    })
    .catch((erro) => {
      this.exibirMensagem('Erro ao salvar o registro! Erro:  ' + erro['message']);
    })
  }

   Copy() {
    const Url = document.getElementById("url") as HTMLInputElement;
    Url.innerHTML = window.location.href;
    console.log(Url.innerHTML);
    console.log(window.location.href);

    Url.select();
    document.execCommand("copy");
  }

}
