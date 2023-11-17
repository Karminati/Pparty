import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddFestaPage } from './add-festa.page';

const routes: Routes = [
  {
    path: '',
    component: AddFestaPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AddFestaPageRoutingModule {}
