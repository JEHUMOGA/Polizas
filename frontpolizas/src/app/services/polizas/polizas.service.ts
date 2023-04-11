import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { Poliza } from 'src/app/models/poliza.interface';

@Injectable({
  providedIn: 'root'
})
export class PolizasService {
  private apiUrl = "http://localhost:8080/poliza/";
  constructor(private http: HttpClient) { }

  public getListaPolizas(): Observable<any>{
    return this.http.get( this.apiUrl + "listapolizas");
  }

  public getListaEmpleados(): Observable<any>{
    return this.http.get(this.apiUrl + "listaempleados")
  }

  public getListaArticulos(): Observable<any>{
    return this.http.get(this.apiUrl + "listaarticulos")
  }

  public getPoliza(idpol:any ): Observable<any>{
    return this.http.get(this.apiUrl + "obtenerpoliza/"+idpol);
  }
  public generarPoliza(poliza:any):Observable<any>{
    const body = JSON.stringify(poliza);
    return this.http.post(this.apiUrl + "generarpoliza", poliza);
  }

  public actualizarPoliza(idpol:number,poliza:any):Observable<any>{
    const body = JSON.stringify(poliza);
    return this.http.put(this.apiUrl + "actualizar/" + idpol, poliza);
  }

  public eliminarPoliza(idpol:number):Observable<any>{
    return this.http.delete(this.apiUrl + "eliminarpoliza/"+idpol);
  }
}
