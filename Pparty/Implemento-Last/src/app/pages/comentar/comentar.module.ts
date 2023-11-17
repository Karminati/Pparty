import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ComentarPageRoutingModule } from './comentar-routing.module';

import { ComentarPage } from './comentar.page';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ComentarPageRoutingModule,
    ReactiveFormsModule,
    RouterLink
  ],
  declarations: [ComentarPage]
})
export class ComentarPageModule {}
