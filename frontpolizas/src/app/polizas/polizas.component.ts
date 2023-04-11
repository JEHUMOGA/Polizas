import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { PolizasService } from '../services/polizas/polizas.service';
import { Poliza } from '../models/poliza.interface';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-polizas',
  templateUrl: './polizas.component.html',
  styleUrls: ['./polizas.component.css']
})
export class PolizasComponent implements OnInit{
  formAgregar:FormGroup;

  headers = [
    'ID', 'SKU', 'Nombre Articulo', 'Cantidad'
  ];
  search = '';
  listapolizas: any;
  listaempleados:any;
  listaarticulos:any;

  poliza:any;
  constructor(
    private router: Router,
    private http: HttpClient,
    private polizasService: PolizasService,
    public formBuilder: FormBuilder,
    ) {
      this.formAgregar = formBuilder.group({
        empleado: ['', Validators.required],
        articulo: ['', Validators.required],
        cantidad: ''
      });
    }
    polizasFiltradas:any [] = [];
    
    filtrarDatos(){
      this.polizasFiltradas = this.listapolizas.filter(
        (item:any) => item.idpolizas ||item.sku || item.nombrearticulo);
    }
    Search() {
      this.polizasFiltradas = this.listapolizas.filter(
        (item:any) => item.idpolizas.toString().toLowerCase().includes(this.search) ||
        item.sku.toString().toLowerCase().includes(this.search) || 
        item.nombrearticulo.toString().toUpperCase().includes(this.search) || 
        item.nombrearticulo.toString().toLowerCase().includes(this.search));
    }
  ngOnInit(){

    this.polizasService.getListaPolizas().subscribe(res => {
      this.listapolizas = res.data.listapolizas;
      this.filtrarDatos();
      
    }, 
    error => {console.error(error)}
    );

    this.polizasService.getListaEmpleados().subscribe(res=>{
      this.listaempleados = res.data.listaEmpleados;
    },
    error => console.error(error));
    this.polizasService.getListaArticulos().subscribe(res=>{
      this.listaarticulos = res.data.listaArticulos;
    },
    error => console.error(error));
  }

  verDetalle(idpol:any, sku:any){
    localStorage.setItem("idpol", idpol);
    localStorage.setItem("sku", sku)
    this.router.navigate(['/detalleArticulo']);
  }

  guardar(){
    let jsonPost = {
      empleadogenero: this.formAgregar.get('empleado')?.value,
      sku: this.formAgregar.get('articulo')?.value,
      cantidad: this.formAgregar.get('cantidad')?.value
    }

    this.polizasService.generarPoliza(jsonPost).subscribe(res =>{
      if(res.meta.status === "OK"){
        this.listapolizas.push({
          idpolizas: res.data.poliza.idpoliza,
          sku: res.data.detalleArticulo.sku,
          nombrearticulo: res.data.detalleArticulo.nombre,
          cantidad: res.data.poliza.cantidad          
        })
        this.filtrarDatos();
        this.msgAlert('success', 'Poliza Generada', '');
      }else {
        this.msgAlert('error', 'Error', res.data.mensaje);
      }
    },
    error => console.error(error))

    this.formAgregar.reset();
  }

  eliminar(idpol:number){
    this.polizasService.eliminarPoliza(idpol).subscribe(res =>{
      if(res.meta.status==="OK"){
        let indice = this.listapolizas.findIndex((p: { idpolizas: number; }) => p.idpolizas === idpol);
        this.listapolizas.splice(indice, 1);
        this.filtrarDatos()
        this.msgAlert('success', 'Poliza Eliminada', res.data.mensaje);
      }else{
        this.msgAlert('error', 'Error', res.data.mensaje);
      }
    })
  }

  confirmarEliminacion(idpol:number){
    Swal.fire({
      title: '¿Seguro que desea eliminar la poliza ' + idpol + ' ?',
      showCancelButton: true,
      confirmButtonText: 'Si',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.isConfirmed) {
        this.eliminar(idpol);
      } 
    })
  }

  msgAlert = (icon: any, title: any, text: any) => {
    Swal.fire({
      icon,
      title,
      text
    })
  }
}
