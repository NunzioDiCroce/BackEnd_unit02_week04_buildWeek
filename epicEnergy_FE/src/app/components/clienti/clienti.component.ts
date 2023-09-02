import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, Pipe } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthData } from 'src/app/auth/auth-data.interface';
import { AuthService } from 'src/app/auth/auth.service';
import { TokenInterceptor } from 'src/app/auth/token.interceptor';
import { Cliente } from 'src/app/model/clienti.interface';
import { Utente } from 'src/app/model/utente.interface';
import { EpicEnergyServiceService } from 'src/app/service/epic-energy-service.service';

@Component({
  selector: 'app-clienti',
  templateUrl: './clienti.component.html',
  styleUrls: ['./clienti.component.scss']
})
export class ClientiComponent implements OnInit {
  subClienti!: Subscription;
  user!: AuthData | null;
  clienti: Cliente[] | undefined;
  sub!: Subscription;
  token!: string | null;

  constructor(private clientiSrv: EpicEnergyServiceService, private authSrv: AuthService) { }

  ngOnInit(): void {


    this.authSrv.user$.subscribe((_user) => {
        this.token = localStorage.getItem('utente');
        console.log(this.token)
      this.user = _user;
      console.log(this.user);

      // Estrai il token se l'utente esiste
      if (this.user) {
        console.log('Token:', this.user.token);
      }
    });

    this.subClienti = this.clientiSrv.getAllClienti().subscribe(
        (clienti: Cliente[]) => {
          this.clienti = clienti;
          console.log(this.clienti);
          console.log(5);
        },
        (error: HttpErrorResponse) => {

          console.error('Errore nella richiesta HTTP:',error);
        }
      );

  }
}
