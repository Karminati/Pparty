import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { FestasSalvasPageRoutingModule } from './festas-salvas-routing.module';

import { FestasSalvasPage } from './festas-salvas.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    FestasSalvasPageRoutingModule,
    RouterLink
  ],
  declarations: [FestasSalvasPage]
})
export class FestasSalvasPageModule {}
