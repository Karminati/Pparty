import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { VisualizarFestaPageRoutingModule } from './visualizar-festa-routing.module';

import { VisualizarFestaPage } from './visualizar-festa.page';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    VisualizarFestaPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [VisualizarFestaPage]
})
export class VisualizarFestaPageModule {}
