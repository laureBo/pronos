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
import { ConfigComponent } from './components/config/config.component';
import { UserConnectionComponent } from './components/user/user-connection/user-connection.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateEditUserComponent } from './components/user/create-edit-user/create-edit-user.component';
import { CurrentSessionComponent } from './components/sessions/current-session/current-session.component';
import { MaterialModule } from './material.module';
import { MatchComponent } from './components/match/match.component';

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
    UserConnectionComponent,
    ConfigComponent,
    CreateEditUserComponent,
    CurrentSessionComponent,
    MatchComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MaterialModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
