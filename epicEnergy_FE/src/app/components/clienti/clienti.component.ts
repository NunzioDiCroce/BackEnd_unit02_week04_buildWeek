import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthData } from 'src/app/auth/auth-data.interface';
import { AuthService } from 'src/app/auth/auth.service';
import { Cliente } from 'src/app/model/clienti.interface';
import { EpicEnergyServiceService } from 'src/app/service/epic-energy-service.service';

@Component({
  selector: 'app-clienti',
  templateUrl: './clienti.component.html',
  styleUrls: ['./clienti.component.scss']
})
export class ClientiComponent implements OnInit {
  user!: AuthData | null;
  clienti: Cliente[] = []; // Inizializza l'array clienti
  token!: string | null;

  constructor(private clientiSrv: EpicEnergyServiceService, private authSrv: AuthService) { }

  ngOnInit(): void {
    this.authSrv.user$.subscribe((_user) => {
      this.token = localStorage.getItem('utente');
      console.log(this.token);
      this.user = _user;
      console.log(this.user);

      // Estrai il token se l'utente esiste
      if (this.user) {
        console.log('Token:', this.user.token);
      }
    });

    this.clientiSrv.getAllClienti().subscribe(
      (data: any) => {
        // Verifica se data è un array prima di assegnarlo a clienti
        if (Array.isArray(data.content)) {
          this.clienti = data.content;
          console.log(this.clienti);
        } else {
          console.error('I dati ricevuti non sono un array:', data);
        }
      },
      (error: HttpErrorResponse) => {
        console.error('Errore nella richiesta HTTP:', error);
      }
    );
  }
}
