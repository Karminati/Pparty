import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { MinhasFestasPageRoutingModule } from './minhas-festas-routing.module';

import { MinhasFestasPage } from './minhas-festas.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MinhasFestasPageRoutingModule
  ],
  declarations: [MinhasFestasPage]
})
export class MinhasFestasPageModule {}
