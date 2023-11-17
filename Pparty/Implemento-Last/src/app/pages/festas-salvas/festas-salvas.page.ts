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


@Component({
  selector: 'app-festas-salvas',
  templateUrl: './festas-salvas.page.html',
  styleUrls: ['./festas-salvas.page.scss'],
})
export class FestasSalvasPage implements OnInit {

  usuario: Usuario;
  avaliacoes: Avaliacao[];
  festas: Festa[];

  constructor(private toastController: ToastController, private navController: NavController, private alertController: AlertController, private festaService: FestaService, private loadingController: LoadingController, private avaliacaoService: AvaliacaoService) { 
    this.avaliacoes = [];
    this.festas = [];

    this.usuario = JSON.parse(localStorage.getItem('usuario') || '[]');
  }

  ngOnInit() {
  }

  async ionViewWillEnter(){
    this.carregarLista();
  }  

  async carregarLista(){
    this.exibirLoader();
    let idUsuario = this.usuario.idUsuario;
    await this.avaliacaoService.listar(idUsuario)
    .then((json)=>{
      this.avaliacoes = <Avaliacao[]> (json);
    });

    for(let i = 0; i < this.avaliacoes.length; i++){
      let idDaFesta = this.avaliacoes[i].tbFesta_idFesta;
      await this.festaService.buscarPorId(parseInt(idDaFesta.toString()))
      .then((json)=>{
        this.festas[i] = <Festa> (json);
      });
      console.log(this.festas[i]);
      
    }

    console.log('aaaaa');
    // for (const festa of this.festas) {
    //   await this.fotoService.listarPorIdArvore(arvore.id).then((json) => {
    //     let fotos = <Foto[]>(json);
    //     if(fotos[0] == null || fotos[0].imagem === ""){
    //       let foto: Foto;
    //       foto = this.fotoAux;
    //       this.fotos.push(foto);
    //     }else{
    //       this.fotos.push(fotos[0])
    //     }
    //   });
    //   this.fecharLoader();
    // }

    this.fecharLoader();
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

  async exibirMensagem(texto: string) {
    const toast = await this.toastController.create({
      message: texto,
      duration: 1500
    });
    toast.present();
  }  


}
