package com.example.ssc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_asignar_estrella.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RedimirEstrella : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redimir_estrella)


        val qr = intent.getStringExtra("QR")
        etTexto.setText(qr?.toString())

        //Variable temp en lo que hacemos la base de datos
        var estrellasDelUser =7

        val btnRedimir = findViewById<Button>(R.id.btnRedimirEstrella)
        btnRedimir.setOnClickListener{
            if(estrellasDelUser >= 6){
                val mov = Movimiento("Redimición","Sergio Alberto Negrete", getDate())
                AsignarEstrella.global.movimientos.add(mov)
                estrellasDelUser -= 6
            }else{
                Toast.makeText(this, "El usuario no cuenta con las estrellas necesarias", Toast.LENGTH_SHORT).show()
            }

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
