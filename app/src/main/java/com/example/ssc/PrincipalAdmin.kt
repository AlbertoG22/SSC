package com.example.ssc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.integration.android.IntentIntegrator

class PrincipalAdmin : AppCompatActivity(){

    //es parte de la librería que sirve para leer los códigos
    lateinit var intentIntegrator: IntentIntegrator
    var a = false
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_admin)

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)

        intentIntegrator = IntentIntegrator(this)

        val asignar = findViewById<Button>(R.id.btnAsignar)
        asignar.setOnClickListener{
            a = false
            intentIntegrator
                .setPrompt("Enfoque el código QR")
                .setOrientationLocked(false)
                .initiateScan()
        }

        val redimir = findViewById<Button>(R.id.btnRedimir)
        asignar.setOnClickListener{
            a = true
            intentIntegrator
                .setPrompt("Enfoque el código QR")
                .setOrientationLocked(false)
                .initiateScan()
        }

        val bit = findViewById<Button>(R.id.btnBitacora)
        bit.setOnClickListener{
            startActivity(Intent(this, Bitacora::class.java))
        }
        auth = FirebaseAuth.getInstance()

        val user = auth.currentUser
        val name = user?.displayName
        tvBienvenida.setText("Hola!" + " " + name)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(intentResult!=null){
            if(intentResult.contents != null){
                if(a==false){
                    startActivity(Intent(this, AsignarEstrella::class.java).putExtra("QR",intentResult.contents))
                }else{
                    startActivity(Intent(this, RedimirEstrella::class.java).putExtra("QR",intentResult.contents))
                }
            }else{
                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

