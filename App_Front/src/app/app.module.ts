import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { ClubComponent } from './club/club.component';
import { HomeComponent } from './home/home.component';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { ResultsComponent } from './results/results.component';
import { SportComponent } from './sport/sport.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ClubComponent,
    NavbarComponent,
    FooterComponent,
    ResultsComponent,
    SportComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([
      {
        path: '',
        component: HomeComponent
    },
    {
        path: 'club',
        component: ClubComponent
    },
    {
      path: 'results',
      component: ResultsComponent
    },
    {
      path: 'sport',
      component: SportComponent
    },
    {
        path: '**',
        redirectTo: 'landing'
    }
   ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
