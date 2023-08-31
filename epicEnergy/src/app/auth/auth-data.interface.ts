export interface AuthData {
  accessToken: string;
  utente: {
    password: string;
    email: string;
    nome: string;
    cognome: string;
    username: string;
  };
}
