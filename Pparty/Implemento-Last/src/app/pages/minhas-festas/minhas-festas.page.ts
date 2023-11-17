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

@Component({
  selector: 'app-minhas-festas',
  templateUrl: './minhas-festas.page.html',
  styleUrls: ['./minhas-festas.page.scss'],
})
export class MinhasFestasPage implements OnInit {

  usuario: Usuario;
  festas: Festa[];


  constructor(private toastController: ToastController, private navController: NavController, private alertController: AlertController, private festaService: FestaService, private loadingController: LoadingController) { 
    this.festas = [];
    
    this.usuario = JSON.parse(localStorage.getItem('usuario') || '[]');
    console.log(this.usuario.idUsuario)
    console.log("aaaaaaa")
  }

  ngOnInit() {
  }

  
  async ionViewWillEnter(){
    this.carregarLista();
  }  

  async carregarLista(){
    this.exibirLoader();
    let idUsuario = this.usuario.idUsuario;
    await this.festaService.listar(idUsuario).then((json)=>{
      this.festas = <Festa[]> (json);
    });

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
