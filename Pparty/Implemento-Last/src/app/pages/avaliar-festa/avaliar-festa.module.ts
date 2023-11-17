import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AvaliarFestaPageRoutingModule } from './avaliar-festa-routing.module';

import { AvaliarFestaPage } from './avaliar-festa.page';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AvaliarFestaPageRoutingModule,
    ReactiveFormsModule,
    RouterLink
  ],
  declarations: [AvaliarFestaPage]
})
export class AvaliarFestaPageModule {}
