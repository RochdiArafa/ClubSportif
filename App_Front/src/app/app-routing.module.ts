import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClubComponent } from './club/club.component';
import { HomeComponent } from './home/home.component';

export const appRouteList: Routes = [
    {
        path: '/home',
        component: HomeComponent
    },
    {
        path: '/club',
        component: ClubComponent
    },
    {
        path: '**',
        redirectTo: 'landing'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(appRouteList)],
    exports: [RouterModule],
    declarations: []
  })
  export class AppRoutingModule { }
