import { Component, OnInit } from '@angular/core';
import { Festa } from 'src/app/model/festa';
import { FestaService } from 'src/app/services/festa.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { ActivatedRoute } from '@angular/router';
import { ToastController } from '@ionic/angular';
import { NavController } from '@ionic/angular';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Geolocation, PositionOptions } from '@capacitor/geolocation';
import { Usuario } from 'src/app/model/usuario';


@Component({
  selector: 'app-add-festa',
  templateUrl: './add-festa.page.html',
  styleUrls: ['./add-festa.page.scss'],
})
export class AddFestaPage implements OnInit {

  festa: Festa;
  usuario: Usuario;
  formGroup: FormGroup;

  latitude: number;
  longitude: number;

  constructor(private activatedRoute: ActivatedRoute, private toastController: ToastController, private navController: NavController, private formBuilder: FormBuilder, private festaService: FestaService, private usuarioService: UsuarioService) {
    this.festa = new Festa();
    this.latitude = 0;
    this.longitude = 0;

    this.formGroup = this.formBuilder.group(
      {
        'titulo': [this.festa.titulo, Validators.compose([
          Validators.required
        ])],
        'endereco': [this.festa.enderecofesta, Validators.compose([
          Validators.required
        ])],
        'data': [this.festa.data, Validators.compose([
          Validators.required
        ])],
        'valorIngresso': [this.festa.valorIngresso, Validators.compose([
          Validators.required
        ])],
        'cardapio': [this.festa.cardapio, Validators.compose([
          Validators.required
        ])],
        'qingresso': [this.festa.qingresso, Validators.compose([
          Validators.required
        ])],
        'categoria': [this.festa.categoria, Validators.compose([
          Validators.required
        ])],
        'latitude': [this.latitude, Validators.compose([
        ])],
        'longitude': [this.longitude, Validators.compose([
        ])]
      }
    )

    this.usuario = JSON.parse(localStorage.getItem('usuario') || '[]');
    console.log(this.usuario.idUsuario);
    let id = this.activatedRoute.snapshot.params['id'];

    if (id != null) {
      this.festaService.buscarPorId(parseInt(id))
        .then((json) => {
          this.festa = <Festa>(json);
          this.formGroup.get('titulo')?.setValue(this.festa.titulo);
          this.formGroup.get('endereco')?.setValue(this.festa.enderecofesta);
          this.formGroup.get('data')?.setValue(this.festa.data);
          this.formGroup.get('valorIngresso')?.setValue(this.festa.valorIngresso);
          this.formGroup.get('cardapio')?.setValue(this.festa.cardapio);
          this.formGroup.get('qingresso')?.setValue(this.festa.qingresso);
          this.formGroup.get('categoria')?.setValue(this.festa.categoria);
        })
        .catch((erro) => {
          this.exibirMensagem('Erro ao recuperar o rgistro! Erro:  ' + erro['message']);
        })
    }
  }

  ngOnInit() {
  }

  salvar() {
    this.festa.titulo = this.formGroup.value.titulo;
    this.festa.enderecofesta = this.formGroup.value.endereco;
    this.festa.latitude = this.formGroup.value.latitude;
    this.festa.longitude = this.formGroup.value.longitude;
    this.festa.data = this.formGroup.value.data;
    this.festa.valorIngresso = this.formGroup.value.valorIngresso;
    this.festa.cardapio = this.formGroup.value.cardapio;
    this.festa.qingresso = this.formGroup.value.qingresso;
    this.festa.categoria = this.formGroup.value.categoria;
    this.festa.idUsuariof = this.usuario.idUsuario;

    let atual = new Date();

    console.log(this.festa.data);
    console.log(atual);
    console.log(parseFloat(this.festa.data));
    console.log(parseFloat(atual.toISOString()));

  
    let partesData = this.festa.data.split("-");
    let data = new Date(parseInt(partesData[2]), parseInt(partesData[1]) - 1, parseInt(partesData[0]));
    console.log(data);
    if (data < new Date()){
      this.exibirMensagem('A data da festa nao pode ser uma data passada');
      this.navController.navigateBack('/minhas-festas')
    }



    console.log(this.usuario.idUsuario);
    console.log(this.festa.idUsuariof);
    console.log(this.formGroup.value.data)


    this.festaService.salvar(this.festa)
      .then((json) => {
        this.festa = <Festa>(json);
        if (this.festa) {
          this.exibirMensagem('Registro salvo');
          this.navController.navigateBack('/minhas-festas');
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

  async atualizar() {
    var options: PositionOptions = {
      enableHighAccuracy: true
    }

    Geolocation.getCurrentPosition(options)
      .then((res) => {
        this.latitude = res.coords.latitude;
        this.longitude = res.coords.longitude;
        this.formGroup.get('latitude')?.setValue(this.latitude);
        this.formGroup.get('longitude')?.setValue(this.longitude);
      })
      .catch((erro) => {
        alert(JSON.stringify(erro))
      })
  }

}
