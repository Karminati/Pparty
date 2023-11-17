import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BuscarFestaPage } from './buscar-festa.page';

const routes: Routes = [
  {
    path: '',
    component: BuscarFestaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BuscarFestaPageRoutingModule {}
