import { Component, OnInit, Pipe } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthData } from 'src/app/auth/auth-data.interface';

import { AuthService } from 'src/app/auth/auth.service';
import { Cliente } from 'src/app/model/clienti.interface';
import { Utente } from 'src/app/model/utente.interface';
import { EpicEnergyServiceService } from 'src/app/service/epic-energy-service.service';

@Component({
  selector: 'app-clienti',
  templateUrl: './clienti.component.html',
  styleUrls: ['./clienti.component.scss']
})
export class ClientiComponent implements OnInit{
  subClienti!: Subscription;
  user!: AuthData|null;
  clienti: Cliente[] | undefined;
  sub!: Subscription;

  constructor(private clientiSrv: EpicEnergyServiceService, private authSrv: AuthService) { }

  ngOnInit(): void {
    this.subClienti = this.clientiSrv.getAllClienti().subscribe((clienti: Cliente[]) => {
      this.clienti = this.clienti;
      console.log(this.clienti);
    });
    this.authSrv.user$.subscribe((_user) => {
      this.user = _user;
      console.log(this.user)
    });

    }
}
