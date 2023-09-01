import { Data } from "@angular/router";
import { TipoCliente } from "./tipo-cliente.enum";


export interface Cliente {
    ragioneSociale:string;
	  partitaIva:string;
	  email:string;
	  dataInserimento:Data;
	  dataUltimoContatto:Data;
   fatturaAnnuale:number;
	  pec:string;
	  telefono:string;
	  emailContatto:string;
	  nomeContatto:string;
	  cognomeContatto:string;
	  telefonoContatto:string;
	  tipoCliente:TipoCliente;
}


