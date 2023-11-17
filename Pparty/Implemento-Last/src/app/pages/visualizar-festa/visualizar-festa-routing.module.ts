import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { VisualizarFestaPage } from './visualizar-festa.page';

const routes: Routes = [
  {
    path: '',
    component: VisualizarFestaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VisualizarFestaPageRoutingModule {}
