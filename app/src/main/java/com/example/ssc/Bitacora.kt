package com.example.ssc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import com.example.ssc.AsignarEstrella.global.movimientos
import kotlinx.android.synthetic.main.activity_bitacora.*



class Bitacora : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitacora)

        this.setTitle("Bitácora de movimientos");

        val adapter = AdaptadorCustom(this, movimientos)
        listaMovimientos.adapter = adapter

        listaMovimientos.onItemClickListener =  AdapterView.OnItemClickListener{parent, view, position, id ->
            Toast.makeText(this, movimientos.get(position).tipo, Toast.LENGTH_SHORT)
        }
    }

}
