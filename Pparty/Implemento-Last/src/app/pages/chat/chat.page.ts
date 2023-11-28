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
import { Mensagem } from 'src/app/model/mensagem';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MensagemService } from 'src/app/services/mensagem.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.page.html',
  styleUrls: ['./chat.page.scss'],
})
export class ChatPage implements OnInit {

  usuario: Usuario;
  chat: Chat;
  mensagem: Mensagem;
  mensagens: Mensagem[];
  formGroup: FormGroup;

  idChat: number;
  idUsuario1: number;
  idUsuario2: number;


  constructor(private activatedRoute: ActivatedRoute, private formBuilder: FormBuilder, private toastController: ToastController, private navController: NavController, private alertController: AlertController, private loadingController: LoadingController, private chatService: ChatService, private mensagemService: MensagemService) { 
    this.chat = new Chat();
    this.mensagem = new Mensagem();
    this.mensagens = [];
    this.idUsuario1 = 0;
    this.idUsuario2 = 0;

    this.usuario = JSON.parse(localStorage.getItem('usuario') || '[]');

    this.formGroup = this.formBuilder.group(
      {
        'conteudo': [this.mensagem.conteudo, Validators.compose([
          Validators.required
        ])]
      }
    )

    let id = this.activatedRoute.snapshot.params['id'];
    this.idChat = id;

    this.chatService.buscarChatPorId(parseInt(id)).then((json) =>{
      this.chat = <Chat>(json);
      console.log(this.chat);

      this.idUsuario1 = this.chat.idUsuario1;
      this.idUsuario2 = this.chat.idUsuario2;

      this.mensagemService.consultarPorIdChat(parseInt(id)).then((json) =>{
        this.mensagens = <Mensagem[]>(json);
        console.log(this.mensagens);
      })

    })
  }

  ngOnInit() {
  }

  async salvar(){
    this.mensagem.conteudo = this.formGroup.value.conteudo;
    this.mensagem.tbChat_IdChat = this.chat.idChat;
    this.mensagem.udOrigem = this.usuario.idUsuario;

  await this.mensagemService.salvarMsg(this.mensagem).then((json) =>{
      this.mensagem = <Mensagem>(json);
      console.log(this.mensagem);

        this.mensagemService.consultarPorIdChat(this.chat.idChat).then((json) =>{
          this.mensagens = <Mensagem[]>(json);
          console.log(this.mensagens);
          this.mensagem.conteudo = "";
          this.formGroup.get('conteudo')?.setValue(this.mensagem.conteudo);
        })
    })
    console.log(this.mensagens);
  }

}
