import { Component, OnInit } from '@angular/core';
import { Festa } from 'src/app/model/festa';
import { FestaService } from 'src/app/services/festa.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastController } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import { AlertController } from '@ionic/angular';
import { LoadingController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
// import { FotoService } from 'src/app/services/foto.service';
// import { Foto } from 'src/app/model/foto';
import { Photo } from '@capacitor/camera';
import { Avaliacao } from 'src/app/model/avaliacao';
import { AvaliacaoService } from 'src/app/services/avaliacao.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-avaliar-festa',
  templateUrl: './avaliar-festa.page.html',
  styleUrls: ['./avaliar-festa.page.scss'],
})
export class AvaliarFestaPage implements OnInit {

  avaliacao: Avaliacao;
  festaAux: Festa;
  idAvaliacao: number;
  formGroup: FormGroup;

  constructor(private activatedRoute: ActivatedRoute, private formBuilder: FormBuilder, private toastController: ToastController, private navController: NavController, private alertController: AlertController, private festaService: FestaService, private loadingController: LoadingController, private avaliacaoService: AvaliacaoService) { 
    this.avaliacao = new Avaliacao();
    this.festaAux = new Festa();

    this.formGroup = this.formBuilder.group(
      {
        'titulo': [this.festaAux.titulo, Validators.compose([
          Validators.required, Validators.min(0), Validators.max(5)
        ])],
        'enderecofesta': [this.festaAux.enderecofesta, Validators.compose([
          Validators.required
        ])],
        'quantAvaliacao': [this.avaliacao.quantAvaliacao, Validators.compose([
          Validators.required
        ])]
      }
    )

    let id = this.activatedRoute.snapshot.params['id'];
    this.idAvaliacao = id;

    this.avaliacaoService.buscarPorId(parseInt(id)).then((json) => 
    {
      this.avaliacao = <Avaliacao>(json);
      console.log(this.avaliacao);

      this.formGroup.get('quantAvaliacao')?.setValue(this.avaliacao.quantAvaliacao);

      this.festaService.buscarPorId(parseInt(this.avaliacao.tbFesta_idFesta.toString()))
      .then((json)=>{
        this.festaAux = <Festa> (json);
        console.log(this.festaAux);
  
        this.formGroup.get('titulo')?.setValue(this.festaAux.titulo);
        this.formGroup.get('enderecofesta')?.setValue(this.festaAux.enderecofesta);
      })
      .catch((erro) => {
        this.exibirMensagem('Erro ao recuperar o registro! Erro:  ' + erro['message']);
      });
   
    })
    .catch((erro) => {
      this.exibirMensagem('Erro ao recuperar o registro! Erro:  ' + erro['message']);
    });

    console.log(this.festaAux);
    console.log(this.avaliacao);
    console.log(this.idAvaliacao);
  }

  ngOnInit() {
  }


  salvar() {
    this.avaliacao.quantAvaliacao = parseInt(this.formGroup.value.quantAvaliacao);

    console.log(this.avaliacao);

    this.avaliacaoService.salvar(this.avaliacao)
      .then((json) => {
        this.avaliacao = <Avaliacao>(json);
        if (this.avaliacao) {
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

  // async ionViewWillEnter(){
  //   this.carregarLista();
  // }  

  // async carregarLista(){
  //   this.exibirLoader();
  //   await this.ocorrenciaService.listar(this.idArvore).then((json)=>{
  //     this.ocorrencias = <Ocorrencia[]> (json);
  //   });
  //   this.fecharLoader();
  // }


  // exibirLoader(){
  //   this.loadingController.create({
  //     message: 'Carregando...'
  //   }).then((res)=>{
  //     res.present();
  //   })
  // }

  // fecharLoader(){
  //   setTimeout(()=>{
  //     this.loadingController.dismiss().then(()=>{
  //     }).catch((erro)=>{
  //       console.log('Erro:  ', erro)
  //     });
  //   }, 500);
  // }

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }  


}
