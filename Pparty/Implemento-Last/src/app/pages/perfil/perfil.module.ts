import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { PerfilPageRoutingModule } from './perfil-routing.module';
import { ReactiveFormsModule } from '@angular/forms';

import { PerfilPage } from './perfil.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    PerfilPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [PerfilPage]
})
export class PerfilPageModule {}
