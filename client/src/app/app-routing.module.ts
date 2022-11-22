import { CurrencyPipe } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BetsComponent } from './components/bets/bets.component';
import { HomeComponent } from './components/home/home.component';
import { CreateSessionComponent } from './components/sessions/create-session/create-session.component';
import { CurrentSessionComponent } from './components/sessions/current-session/current-session.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { CreateEditUserComponent } from './components/user/create-edit-user/create-edit-user.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  { path: 'sessions', component: SessionsComponent },
  { path: 'create-session', component: CreateSessionComponent },
  { path: 'current-session', component: CurrentSessionComponent },
  { path: 'bets', component: BetsComponent },
  { path: 'user', component: CreateEditUserComponent },
  { path: 'user/', component: CreateEditUserComponent },
  { path: 'user/:pseudo', component: CreateEditUserComponent },
  { path: '', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
