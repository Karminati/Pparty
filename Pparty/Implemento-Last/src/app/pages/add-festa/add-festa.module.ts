import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { AddFestaPageRoutingModule } from './add-festa-routing.module';

import { AddFestaPage } from './add-festa.page';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    AddFestaPageRoutingModule,
    ReactiveFormsModule
  ],
  declarations: [AddFestaPage]
})
export class AddFestaPageModule {}
