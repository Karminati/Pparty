import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AvaliarFestaPage } from './avaliar-festa.page';

const routes: Routes = [
  {
    path: '',
    component: AvaliarFestaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AvaliarFestaPageRoutingModule {}
