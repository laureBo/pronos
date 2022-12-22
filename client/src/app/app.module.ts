import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SessionsComponent } from './components/sessions/sessions.component';
import { HeaderComponent } from './components/header/header.component';
import { BetsComponent } from './components/bets/bets.component';
import { SessionSummaryComponent } from './components/sessions/session-summary/session-summary.component';
import { ReactiveFormsModule } from '@angular/forms';
import { UserComponent } from './components/user/user.component';
import { ConfigComponent } from './components/config/config.component';
import { UserConnectionComponent } from './components/user/user-connection/user-connection.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateEditUserComponent } from './components/user/create-edit-user/create-edit-user.component';
import { CurrentSessionComponent } from './components/sessions/current-session/current-session.component';
import { MaterialModule } from './material.module';
import { MatchComponent } from './components/match/match.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { DialogContentDialogComponent } from './components/sessions/dialog-content-dialog/dialog-content-dialog.component';
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SessionsComponent,
    HeaderComponent,
    BetsComponent,
    SessionSummaryComponent,
    UserComponent,
    UserConnectionComponent,
    ConfigComponent,
    CreateEditUserComponent,
    CurrentSessionComponent,
    MatchComponent,
    DialogContentDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MaterialModule,
    BrowserAnimationsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
  exports: [MaterialModule];
}
