import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { BuscarFestaPageRoutingModule } from './buscar-festa-routing.module';

import { BuscarFestaPage } from './buscar-festa.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    BuscarFestaPageRoutingModule
  ],
  declarations: [BuscarFestaPage]
})
export class BuscarFestaPageModule {}
