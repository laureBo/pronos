import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BetsComponent } from './components/bets/bets.component';
import { HomeComponent } from './components/home/home.component';
import { CreateSessionComponent } from './components/sessions/create-session/create-session.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { UserComponent } from './components/user/user.component';

const routes: Routes = [
  { path: 'sessions', component: SessionsComponent },
  { path: 'bets', component: BetsComponent },
  { path: 'create-session', component: CreateSessionComponent },
  { path: 'inscription-form', component: UserComponent },
  { path: '', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
