import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { FestasSalvasPage } from './festas-salvas.page';

const routes: Routes = [
  {
    path: '',
    component: FestasSalvasPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class FestasSalvasPageRoutingModule {}
