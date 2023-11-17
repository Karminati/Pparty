import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'cadastro',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'cadastro',
    loadChildren: () => import('./pages/cadastro/cadastro.module').then( m => m.CadastroPageModule)
  },
  {
    path: 'inicio',
    loadChildren: () => import('./pages/inicio/inicio.module').then( m => m.InicioPageModule)
  },
  {
    path: 'perfil',
    loadChildren: () => import('./pages/perfil/perfil.module').then( m => m.PerfilPageModule)
  },
  {
    path: 'visualizar-festa',
    loadChildren: () => import('./pages/visualizar-festa/visualizar-festa.module').then( m => m.VisualizarFestaPageModule)
  },
  {
    path: 'visualizar-festa/:id',
    loadChildren: () => import('./pages/visualizar-festa/visualizar-festa.module').then( m => m.VisualizarFestaPageModule)
  },
  {
    path: 'add-festa',
    loadChildren: () => import('./pages/add-festa/add-festa.module').then( m => m.AddFestaPageModule)
  },
  {
    path: 'add-festa/:id',
    loadChildren: () => import('./pages/add-festa/add-festa.module').then( m => m.AddFestaPageModule)
  },
  {
    path: 'avaliar-festa',
    loadChildren: () => import('./pages/avaliar-festa/avaliar-festa.module').then( m => m.AvaliarFestaPageModule)
  },
  {
    path: 'avaliar-festa/:id',
    loadChildren: () => import('./pages/avaliar-festa/avaliar-festa.module').then( m => m.AvaliarFestaPageModule)
  },
  {
    path: 'buscar-festa',
    loadChildren: () => import('./pages/buscar-festa/buscar-festa.module').then( m => m.BuscarFestaPageModule)
  },
  {
    path: 'minhas-festas/:id',
    loadChildren: () => import('./pages/minhas-festas/minhas-festas.module').then( m => m.MinhasFestasPageModule)
  },
  {
    path: 'minhas-festas',
    loadChildren: () => import('./pages/minhas-festas/minhas-festas.module').then( m => m.MinhasFestasPageModule)
  },
  {
    path: 'festas-salvas',
    loadChildren: () => import('./pages/festas-salvas/festas-salvas.module').then( m => m.FestasSalvasPageModule)
  },
  {
    path: 'comentar',
    loadChildren: () => import('./pages/comentar/comentar.module').then( m => m.ComentarPageModule)
  },
  {
    path: 'comentar/:id',
    loadChildren: () => import('./pages/comentar/comentar.module').then( m => m.ComentarPageModule)
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
