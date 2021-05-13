package com.example.proyecto10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    BASIC

}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //SETUP
        //recuperar parametros
        val bundle = intent.extras
       val email = bundle?.getString("email")
       val provider =  bundle?.getString("provider")
        setup(email?:"",provider ?:"")
    }


    private fun setup(email:String,provider:String){

        title = "Inicio"
        emailTextView.text=email
        providerTextView.text=provider
        //funcionacidad cierre sesion

        logOutButton.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()

        }
    }



}