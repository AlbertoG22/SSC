package com.example.ssc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.CallbackManager
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.FacebookCallback
import com.facebook.login.widget.LoginButton
import android.content.Intent
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.database.FirebaseDatabase



class Login : AppCompatActivity() {

    var callbackManager = CallbackManager.Factory.create();
    lateinit var auth: FirebaseAuth
    //lateinit var firebaseAuthListener: FirebaseAuth.AuthStateListener
    private lateinit var googleSignInClient: GoogleSignInClient
    private var RC_SIGN_IN = 1
  //  lateinit var progressBar: ProgressBar
    private lateinit var name: EditText
    private lateinit var lastName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       /* val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setOnClickListener{
            iniciarSesionConGoogle()
            //  mensaje("Funciona")
        }
*/
      //  progressBar = findViewById(R.id.progressBar)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val loginButton = findViewById<LoginButton>(R.id.login_button);
        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                mensaje("Operacion cancelada")
            }

            override fun onError(exception: FacebookException) {
                mensaje("Se presentó un error")
            }
        })

        /* name = findViewById<EditText>(R.id.etName)
         lastName = findViewById<EditText>(R.id.etLastName)
         email = findViewById<EditText>(R.id.etEmail)
         password = findViewById<EditText>(R.id.etPassword)

         val btnCreateUser = findViewById<Button>(R.id.btnCreateUser)
         btnCreateUser.setOnClickListener(){
             createNewUser()
         }*/

      //  progressBar = findViewById<ProgressBar>(R.id.progressBar)

        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        //dbReference = database.reference.child("User")
    }

    private fun iniciarSesionConGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                mensaje("Falló inicio de sesión con google")
            }
        }
    }

    fun mensaje(m:String){
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show()
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
       // progressBar.visibility = View.VISIBLE
        login_button.visibility = View.INVISIBLE
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, PrincipalAdmin::class.java))
                } else {
                    mensaje("Falló la autenticación")
                }
              //  progressBar.visibility = View.GONE
                login_button.visibility = View.VISIBLE
            }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    mensaje("Éxito en la autenticación")
                    startActivity(Intent(applicationContext, PrincipalAdmin::class.java))
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    mensaje("Error en la autenticación")
                }
                // ...
            }
    }
}
