package com.example.ssc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ssc.AsignarEstrella.global.movimientos
import kotlinx.android.synthetic.main.activity_asignar_estrella.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AsignarEstrella : AppCompatActivity() {
    object global {
        var movimientos:ArrayList<Movimiento> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asignar_estrella)

        val qr = intent.getStringExtra("QR")
        etTexto.setText(qr?.toString())


        val btnAgregar = findViewById<Button>(R.id.btnAsignarEstrella)
        btnAgregar.setOnClickListener{
            val mov = Movimiento("Asignación","Fernando Alberto Negrete", getDate())
            movimientos.add(mov)
        }

    }

    fun getDate():String{
        val date = Calendar.getInstance().time
        val fecha = date.toString("yyyy/MM/dd HH:mm:ss")

        return fecha
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }
}

