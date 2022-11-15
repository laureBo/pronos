import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { HeaderComponent } from './components/header/header.component';
import { BetsComponent } from './components/bets/bets.component';
import { SessionSummaryComponent } from './components/sessions/session-summary/session-summary.component';
import { CreateSessionComponent } from './components/sessions/create-session/create-session.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserComponent } from './components/user/user.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SessionsComponent,
    HeaderComponent,
    BetsComponent,
    SessionSummaryComponent,
    CreateSessionComponent,
    UserComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, ReactiveFormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
