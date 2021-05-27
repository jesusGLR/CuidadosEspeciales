package com.example.proyecto10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_profile_doc.*
import kotlinx.android.synthetic.main.activity_register.*

import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class registerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ibtn_atras.setOnClickListener{
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }


        //Analytics Event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()

        bundle.putString("message","Integracion de firebase completa")
        analytics.logEvent("UInitScreen",bundle)
            save2.setOnClickListener{
                        if (correoEditText.text.isNotEmpty() && contra.text.isNotEmpty()) {
                            //Firebase instancai

                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(correoEditText.text.toString(),contra.text.toString())
                                .addOnCompleteListener(){

                                    if (it.isSuccessful){
                                        val intent = Intent(this, AuthActivity::class.java)
                                        startActivity(intent)
                                    }
                                    else{

                                        showAlert()
                                    }
                                }
                        }
                    }

    }

    private fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error al usario")
        builder.setPositiveButton("aceptar",null)
        val dialog: AlertDialog =  builder.create()
        dialog.show()

    }
}