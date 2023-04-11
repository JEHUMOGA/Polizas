import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PolizasService } from '../services/polizas/polizas.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-detalle-poliza',
  templateUrl: './detalle-poliza.component.html',
  styleUrls: ['./detalle-poliza.component.css']
})
export class DetallePolizaComponent implements OnInit {
  constructor(
    private polizasService: PolizasService,
    formBuilder:FormBuilder,
  ){
    this.formEditar = formBuilder.group({
      idpoliza: ['', Validators.required],
      empleado: ['', Validators.required],
      sku: ['', Validators.required],
      cantidad: ''
    });
  }
  idpol:any;
  detallePoliza:any;
  poliza:any;
  empleado:any;
  articulo:any;
  selectedEmpleadoName:any;
  formEditar:FormGroup;
  listaempleados:any;
  listaarticulos:any;
  detalleAux:any;
  selectedArticulo:any;
  selectedArticuloName:any;
  cantidadModel:any;
  idpoliza:any;
  band:boolean = true;

  ngOnInit(): void {
    this.idpol = localStorage.getItem("idpol");
    this.detalleAux = this.polizasService.getPoliza(this.idpol).subscribe(res => {
      this.detallePoliza = res.data;
      this.poliza = this.detallePoliza.poliza;
      this.empleado = this.detallePoliza.empleado;
      this.articulo = this.detallePoliza.detalleArticulo; 
      localStorage.setItem('nombre',this.empleado.nombre);
      localStorage.setItem('apellido',this.empleado.apellido);   
      this.idpoliza = this.poliza.idpoliza;
      this. selectedArticulo = this.articulo.sku;
      this.selectedArticuloName = this.articulo.sku;  
      this.cantidadModel = this.poliza.cantidad;
    },
    error => console.error(error));
    this.polizasService.getListaEmpleados().subscribe(res=>{
      this.listaempleados = res.data.listaEmpleados;
      let name = localStorage.getItem('nombre');
      let lastname = localStorage.getItem('apellido');
      let emp = this.listaempleados.findIndex(((p: { nombre:string; apellido:string; }) => p.nombre === name && p.apellido === lastname));
      this.selectedEmpleadoName = res.data.listaEmpleados[emp].idempleado;
    },
    error => console.error(error));
    this.polizasService.getListaArticulos().subscribe(res=>{
      this.listaarticulos = res.data.listaArticulos;
    },
    error => console.error(error));

  }
  cambioEmpleado(){
    console.log(this.selectedEmpleadoName);
  }
  cambioSku(){
    this.selectedArticuloName = this.selectedArticulo;
  }
  cambioName(){
    this.selectedArticulo = this.selectedArticuloName;
  }

  limpiar(){
    localStorage.removeItem('nombre');
    localStorage.removeItem('apellido');   
  }
  visibilidad = true;
  jsonAux:any;
  habilitar(){
    this.jsonAux ={
      "empleadogenero": this.selectedEmpleadoName,
      "sku": this.selectedArticulo,
      "cantidad": this.cantidadModel 
    }
    this.band = false;
  }

  guardar(){
    let poliza ={
      "empleadogenero": this.selectedEmpleadoName,
      "sku": this.selectedArticulo,
      "cantidad": this.cantidadModel 
    }
    this.polizasService.actualizarPoliza(this.idpoliza, poliza).subscribe(res =>{
      if(res.meta.status=== "OK"){
        this.msgAlert('success', 'Poliza Actualizada', res.data.mensaje);
      }
      else{
        this.msgAlert('error', 'Error al Actualizar', res.data.mensaje);
        this.cancelar();
      }
    },
    error => console.error(error));
    this.band = true;
  }
  msgAlert = (icon: any, title: any, text: any) => {
    Swal.fire({
      icon,
      title,
      text
    })
  }

  cancelar(){
    this.selectedEmpleadoName = this.jsonAux.empleadogenero;
    this.selectedArticulo = this.jsonAux.sku;
    this.selectedArticuloName = this.jsonAux.sku;
    this.cantidadModel = this.jsonAux.cantidad;
    this.band = true;
  }

}
