import { CurrencyPipe } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BetsComponent } from './components/bets/bets.component';
import { HomeComponent } from './components/home/home.component';
import { MatchComponent } from './components/match/match.component';
import { CurrentSessionComponent } from './components/sessions/current-session/current-session.component';
import { EditSessionComponent } from './components/sessions/edit-session/edit-session.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { CreateEditUserComponent } from './components/user/create-edit-user/create-edit-user.component';
import { UserConnectionComponent } from './components/user/user-connection/user-connection.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  { path: '', component: UserComponent },
  { path: 'sessions/', component: SessionsComponent },
  { path: 'edit-session/:id', component: EditSessionComponent },
  { path: 'sessions/:id', component: CurrentSessionComponent },
  { path: 'create-match', component: MatchComponent },
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
