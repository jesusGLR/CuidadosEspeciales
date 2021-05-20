package com.example.proyecto10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_profile_doc.*
import java.security.Provider

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

       // super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_auth)

        //Splash
        Thread.sleep(3000)
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)





                //Analytics Event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()

        bundle.putString("message","Integracion de firebase completa")
        analytics.logEvent("UInitScreen",bundle)


        //Setup
        setup()

        //showindex

     //   showIndex()
    }

        private fun setup(){
            title="    Cuidados Especiales - Iniciar sesión"
//            tv_olvide_mi_contrasena.setOnClickListener{
//                val intent = Intent(this, profile_docActivity::class.java)
//                startActivity(intent)
//            }

            signUpbutton.setOnClickListener{
                if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                    //Firebase instancai

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),passwordEditText.text.toString())
                        .addOnCompleteListener(){

                            if (it.isSuccessful){
                                showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                            }
                            else{

                                showAlert()
                            }
                        }
                }
            }

//            loginButton.setOnClickListener {
//                if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
//                //Firebase instancai
//
//                FirebaseAuth.getInstance()
//                    .signInWithEmailAndPassword(emailEditText.text.toString(),passwordEditText.text.toString())
//                    .addOnCompleteListener(){
//
//                        if (it.isSuccessful){
//                            showHome(it.result?.user?.email?:"",ProviderType.BASIC)
//                        }
//                        else{
//
//                            showAlert()
//                        }
//                    }
//            }
//
//            }
        }
                private fun showAlert(){

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Error")
                    builder.setMessage("Se ha producido un error al usario")
                    builder.setPositiveButton("aceptar",null)
                    val dialog:AlertDialog =  builder.create()
                    dialog.show()

                }

                //Muestra el index
        private fun showHome(email:String,provider:ProviderType){

                val profileIntent= Intent(this,profile_docActivity::class.java).apply{
                    putExtra("email",email)
                    putExtra("provider",provider.name)
                }
                startActivity(profileIntent)
        }
            //Muestra el index
             private fun showIndex(){
                    val profileIntent= Intent(this,IndexActivity::class.java).apply{
                }
                       startActivity(profileIntent)


             }


}