import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ComentarPage } from './comentar.page';

const routes: Routes = [
  {
    path: '',
    component: ComentarPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ComentarPageRoutingModule {}
