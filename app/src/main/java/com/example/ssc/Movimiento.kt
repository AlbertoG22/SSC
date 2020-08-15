package com.example.ssc


class Movimiento(tipo:String, nCliente:String, fecha:String) {

    val tipo:String?
    val nCliente:String?
    val fecha:String?

    init{
        this.tipo = tipo
        this.nCliente = nCliente
        this.fecha = fecha
    }
}