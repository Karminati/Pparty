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
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.page.html',
  styleUrls: ['./inicio.page.scss'],
})
export class InicioPage implements OnInit 
{

  usuario: Usuario;
  festas: Festa[];
  festascategoria: Festa[];
  cansei: number;
  formGroup: FormGroup;
  pesquisa: String;


  constructor( private formBuilder: FormBuilder, private toastController: ToastController, private navController: NavController, private alertController: AlertController, private festaService: FestaService, private loadingController: LoadingController) { 
    this.festas = [];
    this.festascategoria = [];
    this.cansei = 0;
    this.pesquisa = "";
    
    this.usuario = JSON.parse(localStorage.getItem('usuario') || '[]');
    console.log(this.usuario.idUsuario)
    console.log("aaaaaaa")
    console.log(this.festas)

    this.formGroup = this.formBuilder.group(
      {
        'pesquisa': [this.pesquisa, Validators.compose([
          Validators.required
        ])]
      }
    )
  }

  ngOnInit() {
  }

  async ionViewWillEnter(){
    if(this.cansei === 0)
    {
    this.carregarLista();
    console.log("é pra aparecer so uma vez")
    }
    else
    {
      this.festas;
      console.log("é isso mesmo pae")
      console.log(this.festas)
    }
  }  

  async carregarLista(){
    this.exibirLoader();
    await this.festaService.listarTd().then((json)=>{
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

  async Grande()
  {  
    await this.festaService.listarTd().then((json)=>{
      this.festas = <Festa[]> (json);
    });
    let j = 0;
    this.festascategoria = []; // Limpa a lista festascategoria
    for (let i = 0; i < this.festas.length; i++) 
    {
      if(this.festas[i].categoria === "Festa Grande")
      {
        this.festascategoria[j] = this.festas[i];
        j++;
      }
      else
      {
      }
    }        
    this.festas = []; // Limpa a lista festas
    this.festas = this.festascategoria; // Atribui os novos itens à lista festas
    this.cansei = 1;
  }

  async Medio()
  {  
    await this.festaService.listarTd().then((json)=>{
      this.festas = <Festa[]> (json);
    });
    let j = 0;
    this.festascategoria = []; // Limpa a lista festascategoria
    for (let i = 0; i < this.festas.length; i++) 
    {
      if(this.festas[i].categoria === "Festa Média")
      {
        this.festascategoria[j] = this.festas[i];
        j++;
      }
      else
      {
      }
    }        
    this.festas = []; // Limpa a lista festas
    this.festas = this.festascategoria; // Atribui os novos itens à lista festas
    this.cansei = 1;
  }

  async Tematica()
  {  
    await this.festaService.listarTd().then((json)=>{
      this.festas = <Festa[]> (json);
    });
    let j = 0;
    this.festascategoria = []; // Limpa a lista festascategoria
    for (let i = 0; i < this.festas.length; i++) 
    {
      if(this.festas[i].categoria === "Festa Temática")
      {
        this.festascategoria[j] = this.festas[i];
        j++;
      }
      else
      {
      }
    }        
    this.festas = []; // Limpa a lista festas
    this.festas = this.festascategoria; // Atribui os novos itens à lista festas
    this.cansei = 1;
  }


  async Diurna()
  {  
    await this.festaService.listarTd().then((json)=>{
      this.festas = <Festa[]> (json);
    });
    let j = 0;
    this.festascategoria = []; // Limpa a lista festascategoria
    for (let i = 0; i < this.festas.length; i++) 
    {
      if(this.festas[i].categoria === "Festa Diurna")
      {
        this.festascategoria[j] = this.festas[i];
        j++;
      }
      else
      {
      }
    }        
    this.festas = []; // Limpa a lista festas
    this.festas = this.festascategoria; // Atribui os novos itens à lista festas
    this.cansei = 1;
  }

  async pesquisar(){
    this.pesquisa = this.formGroup.value.pesquisa;
    console.log(this.pesquisa);

    await this.festaService.filtrarFesta(this.pesquisa)
    .then((json) =>
    {
      this.festas = <Festa[]> (json);
    })

    console.log(this.festas);

    this.cansei = 1;
  }

  
  async Todas()
  {  
    await this.festaService.listarTd().then((json)=>{
      this.festas = <Festa[]> (json);
    });
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
