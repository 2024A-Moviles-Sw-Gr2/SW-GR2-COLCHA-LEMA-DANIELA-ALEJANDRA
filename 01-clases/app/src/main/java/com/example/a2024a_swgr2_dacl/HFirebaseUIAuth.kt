package com.example.a2024a_swgr2_dacl

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class HFirebaseUIAuth : AppCompatActivity() {

    private val respuestaLoginAuthUI = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){
        res: FirebaseAuthUIAuthenticationResult ->
        if(res.resultCode == RESULT_OK){

            if (res.idpResponse != null){
                seLogeo(res.idpResponse!!)
            }
        }
    }

    fun seLogeo(res: IdpResponse){
        val botonLogin = findViewById<Button>(R.id.btn_login_firebase)
        val botonLogout =  findViewById<Button>(R.id.btn_logout_firebase)
        val tvBienvenida = findViewById<TextView>(R.id.tv_bienvenido)

        tvBienvenida.text = FirebaseAuth.getInstance().currentUser?.displayName
        botonLogin.visibility = View.INVISIBLE
        botonLogout.visibility = View.VISIBLE

        if(res.isNewUser){
            registrarUsuarioPrimeraVez(res)
        }
    }

    private fun registrarUsuarioPrimeraVez(res: IdpResponse) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hfirebase_uiauth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonLogin = findViewById<Button>(R.id.btn_login_firebase)
        val botonLogout =  findViewById<Button>(R.id.btn_logout_firebase)
        val tvBienvenida = findViewById<TextView>(R.id.tv_bienvenido)

        botonLogin.setOnClickListener {
            val providers = arrayListOf(
                AuthUI.IdpConfig.EmailBuilder().build()
            )

            val logearseIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            respuestaLoginAuthUI.launch(logearseIntent)
        }

        botonLogout.setOnClickListener {
            tvBienvenida.text = "Bienvenido"
            botonLogin.visibility = View.VISIBLE
            botonLogout.visibility = View.INVISIBLE

            FirebaseAuth.getInstance().signOut()
        }

        val usuario = FirebaseAuth.getInstance().currentUser

        if (usuario != null){
            tvBienvenida.text = FirebaseAuth.getInstance().currentUser?.displayName
            botonLogin.visibility = View.INVISIBLE
            botonLogout.visibility = View.VISIBLE
        }
    }
}