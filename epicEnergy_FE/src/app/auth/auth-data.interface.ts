import { Ruolo } from "../model/ruolo";

export interface AuthData {

  token: string;
  utente: {
    password: string;
    email: string;
    nome: string;
    cognome: string;
    username: string;
    ruolo: Ruolo;
    ruoloNome: string;
    id:string
  };
}
