import { Component, OnInit } from '@angular/core';
import { Comentario } from 'src/app/model/comentario';
import { ComentarioService } from 'src/app/services/comentario.service';

import { ActivatedRoute } from '@angular/router';
import { ToastController } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { getLocaleDateFormat } from '@angular/common';

import { LoadingController } from '@ionic/angular';
import { AlertController } from '@ionic/angular';


@Component({
  selector: 'app-comentar',
  templateUrl: './comentar.page.html',
  styleUrls: ['./comentar.page.scss'],
})
export class ComentarPage implements OnInit {

  comentario: Comentario;
  formGroup: FormGroup;

  constructor(private alertController: AlertController, private loadingController: LoadingController, private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private comentarioService: ComentarioService) {
    this.comentario = new Comentario() 

    this.formGroup = this.formBuilder.group(
      {
        'coment': [this.comentario.coment, Validators.compose([
          Validators.required, Validators.minLength(1)
        ])],
      }
    )

    
    let id = this.activatedRoute.snapshot.params['id'];
    this.comentario.idAvaliacaoc = id;

    let idAv = this.comentario.idAvaliacaoc;

    this.comentarioService.buscarPorIdAvaliacao(parseInt(id))
    .then((json) =>{
      this.comentario = <Comentario>(json);
      this.formGroup.get('coment')?.setValue(this.comentario.coment);
      console.log(this.comentario);
    }).catch((erro) => {
      this.exibirMensagem('Erro ao recuperar o registro! Erro:  ' + erro['message']);
    })

  }

  ngOnInit() {
  }


  salvar() {
    this.comentario.coment = this.formGroup.value.coment;
    console.log(this.comentario);

    this.comentarioService.salvar(this.comentario)
      .then((json) => {
        this.comentario = <Comentario>(json);
        console.log(this.comentario)
        if (this.comentario) {
          this.exibirMensagem('Registro salvo');
          this.navController.navigateBack('/festas-salvas');
        } else {
          this.exibirMensagem('Erro ao salvar o registro');
        }
      })
      .catch((erro) => {
        this.exibirMensagem('Erro ao salvar o registro! Erro:  ' + erro['message']);
      })
  }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }

}
