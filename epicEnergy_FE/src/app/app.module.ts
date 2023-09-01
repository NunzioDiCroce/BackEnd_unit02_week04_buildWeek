import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterComponent } from './auth/register/register.component';
import { LoginComponent } from './auth/login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthGuard } from './auth/auth.guard';
import { ClientiComponent } from './components/clienti/clienti.component';



const routes: Routes= [
  {
    path: '',
    component: HomeComponent,

  },
  {
    path:'login',
    component:LoginComponent,

  },
  {
    path:'register',
    component: RegisterComponent,

  },
  {
    path:'clienti',
    component: ClientiComponent,
    canActivate: [AuthGuard],
  },
  {
    path:'**',
redirectTo:'',
  //  canActivate: [AuthGuard]
  }
]
@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    RegisterComponent,
    LoginComponent,
    ClientiComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
