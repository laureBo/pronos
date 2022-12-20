import { CurrencyPipe } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BetsComponent } from './components/bets/bets.component';
import { HomeComponent } from './components/home/home.component';
import { MatchComponent } from './components/match/match.component';
import { CreateSessionComponent } from './components/sessions/create-session/create-session.component';
import { CurrentSessionComponent } from './components/sessions/current-session/current-session.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { CreateEditUserComponent } from './components/user/create-edit-user/create-edit-user.component';
import { UserConnectionComponent } from './components/user/user-connection/user-connection.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  { path: '', component: UserComponent },
  { path: 'sessions', component: SessionsComponent },
  { path: 'create-session', component: CreateSessionComponent },
  { path: 'create-match', component: MatchComponent },
  { path: 'current-session', component: CurrentSessionComponent },
  { path: 'bets', component: BetsComponent },
  { path: 'create-user', component: CreateEditUserComponent },
  { path: 'connection', component: UserConnectionComponent },
  { path: 'user/', component: CreateEditUserComponent },
  { path: 'user/:pseudo', component: CreateEditUserComponent },
  { path: 'home', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
