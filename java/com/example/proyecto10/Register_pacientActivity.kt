package com.example.proyecto10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_pacient.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile_doc.*
import com.google.firebase.database.DatabaseReference

class Register_pacientActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_pacient)
        //establecer titulo
        title = "Registrar paciente"
        //obtener valor del email mediante el bundle
        val bundle = intent.extras
        val email = bundle?.getString("email")

      //  nombrePaciente.setText(email)
        //regresar al menu princial (BACK)
        btn_atras.setOnClickListener {
            val intent = Intent(this, profile_docActivity::class.java)
            startActivity(intent)
        }

        //Guardar datos (llamar al boton)
        saveButton.setOnClickListener{
            //crear coleccion
        db.collection("paciente").document().set(
         //   val map13 = hashMapOf<email, Any?>()
            //datos
            hashMapOf(
                 "nombre" to nombrePaciente.text.toString(),
                 "edad" to edad.text.toString(),
                 "peso" to peso.text.toString(),
                 "estatura" to estatura.text.toString(),
                 "padecimiento" to padecimientos.text.toString(),
                 "email" to email!!
             )
            )

            super.onBackPressed();
            //val map1 = HashMapOf<String, ArrayList<String>>()


        }





    }
    private fun setup(email:String){

        title = "Registrar paciente"
         nombrePaciente.setText(email)
        // providerTextView.text=provider
        //funcionacidad cierre sesion


    }


}