import { Component, OnInit } from '@angular/core';
import { Festa } from 'src/app/model/festa';
import { FestaService } from 'src/app/services/festa.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ToastController } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import { AlertController } from '@ionic/angular';
import { LoadingController } from '@ionic/angular';
import { Usuario } from 'src/app/model/usuario';
import { Chat } from 'src/app/model/chat';
import { ChatService } from 'src/app/services/chat.service';

@Component({
  selector: 'app-meus-chats',
  templateUrl: './meus-chats.page.html',
  styleUrls: ['./meus-chats.page.scss'],
})
export class MeusChatsPage implements OnInit {

  usuario: Usuario;
  chats: Chat[];
  usuarios: Usuario[];

  constructor(private usuarioService: UsuarioService, private toastController: ToastController, private navController: NavController, private alertController: AlertController, private chatService: ChatService, private loadingController: LoadingController) { 
    this.chats = [];
    this.usuarios = [];
    
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
    await this.chatService.buscarChatPorIdUsuario(idUsuario).then((json)=>{
      this.chats = <Chat[]> (json);
    });

    for(let i = 0; i < this.chats.length; i++){
      let idDoUsuario = 0;
      if(idUsuario == this.chats[i].idUsuario1){
        idDoUsuario = this.chats[i].idUsuario2;
      }else{
        idDoUsuario = this.chats[i].idUsuario1;
      }
      console.log(idDoUsuario);
      await this.usuarioService.consultarPorId(parseInt(idDoUsuario.toString()))
      .then((json)=>{
        this.usuarios[i] = <Usuario> (json);
      });
      console.log(this.usuarios[i]);
      
    }

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
