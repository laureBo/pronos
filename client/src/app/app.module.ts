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
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { CreateEditUserComponent } from './components/user/create-edit-user/create-edit-user.component';
import { CurrentSessionComponent } from './components/sessions/current-session/current-session.component';
import { MaterialModule } from './material.module';
import { MatchComponent } from './components/match/match.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { DialogContentDialogComponent } from './components/sessions/dialog-content-dialog/dialog-content-dialog.component';
import { EditSessionComponent } from './components/sessions/edit-session/edit-session.component';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { BasicAuthInterceptor } from './basic-auth-interceptor';
import { MessageService } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { TabViewModule } from 'primeng/tabview';
import { RippleModule } from 'primeng/ripple';
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
    EditSessionComponent,
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
    TableModule,
    ToastModule,
    MessagesModule,
    MessageModule,
    ButtonModule,
    InputTextModule,
    TabViewModule,
    RippleModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    MessageService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
  exports: [MaterialModule];
}
