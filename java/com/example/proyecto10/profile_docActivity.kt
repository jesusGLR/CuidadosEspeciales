package com.example.proyecto10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_profile_doc.*
import kotlinx.android.synthetic.main.activity_register.*


enum class ProviderType{
    BASIC

}


class profile_docActivity : AppCompatActivity() {



    //private val db = FirebaseFirestore.getInstance()
/*
    private lateinit var dbreference: DatabaseReference
    lateinit var listaPacientes: MutableList<Object>
    lateinit var listView: ListView
*/

    private lateinit var adapter:MainAdapter

    private val rotateOpen: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim)}
    private val rotateClose: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim)}
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim)}
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim)}

    private var clicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_doc)
        //SETUP
        //recuperar parametros
        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider =  bundle?.getString("provider")

        /*
        ///iterar pacientes en la lista en el front
        listaPacientes  =  mutableListOf()
        listView = findViewById(R.id.listaPacienes)
        dbreference =  FirebaseDatabase.getInstance().getReference("paciente")
        //luego de tener la base de datos instanciada base
        */

        //FirebaseFirestore.getInstance().getReference("paciente")
        setup(email?:"",provider ?:"")
////////

         //AGREGAR  recycler view
        adapter = MainAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val dummyList:MutableList<Paciente> = mutableListOf<Paciente>()
        dummyList.add(Paciente("https://hatrabbits.com/wp-content/uploads/2017/01/random.jpg",
                    "Paciente 1","Emferdedad de prueba"))

        adapter.setListaData(dummyList)
        adapter.notifyDataSetChanged()


//////////


        ibtn_abrir.setOnClickListener{
            onAddButtonClicked()
        }
        ibtn_botiquin.setOnClickListener{
            val intent = Intent(this, BitacoraActivity::class.java)
            startActivity(intent)
        }
        ibtn_exit.setOnClickListener{
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
        ibtn_buscar.setOnClickListener{
            val intent = Intent(this, historyActivity::class.java)
            startActivity(intent)
        }
        ibtn_ambulance.setOnClickListener{
            val intent = Intent(this, ambulanceActivity::class.java)
            startActivity(intent)
        }

        ibtn_add_paciente.setOnClickListener{

        ///   val intent = Intent(this, Register_pacientActivity::class.java)
        //    startActivity(intent)

            val profileIntent= Intent(this,Register_pacientActivity::class.java).apply{
                putExtra("email",email)

                //  putExtra("provider",provider.name)
           }
            //iniciar pantalla de registrar cliente
           startActivity(profileIntent)
        }

        ibtn_exit.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()

        }

     }


    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        setClickable(clicked)
        clicked = !clicked
    }

    private fun setAnimation(clicked: Boolean) {
        if (!clicked){
            ibtn_buscar.startAnimation(fromBottom)
            ibtn_botiquin.startAnimation(fromBottom)
            ibtn_ambulance.startAnimation(fromBottom)
            ibtn_abrir.startAnimation(rotateOpen)
        }else{
            ibtn_buscar.startAnimation(toBottom)
            ibtn_botiquin.startAnimation(toBottom)
            ibtn_ambulance.startAnimation(toBottom)
            ibtn_abrir.startAnimation(rotateClose)
        }
    }
    private fun showActivityPacient(email:String,provider:ProviderType){

        val profileIntent= Intent(this,Register_pacientActivity::class.java).apply{
            putExtra("email",email)
            putExtra("provider",provider.name)
        }
        startActivity(profileIntent)
    }

    private fun setup(email:String,provider:String){

        title = "Inicio"
        textView15.text=email
       // providerTextView.text=provider
        //funcionacidad cierre sesion

        ibtn_exit.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressed()

        }
    }


    private fun setVisibility(clicked: Boolean) {
        if (!clicked){
            ibtn_ambulance.visibility = View.VISIBLE
            ibtn_botiquin.visibility = View.VISIBLE
            ibtn_buscar.visibility = View.VISIBLE
        }else{
            ibtn_ambulance.visibility = View.INVISIBLE
            ibtn_botiquin.visibility = View.INVISIBLE
            ibtn_buscar.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(clicked: Boolean){
        if(!clicked){
            ibtn_ambulance.isClickable = true
            ibtn_botiquin.isClickable = true
            ibtn_buscar.isClickable = true

        }else{
            ibtn_ambulance.isClickable = false
            ibtn_botiquin.isClickable = false
            ibtn_buscar.isClickable = false
        }
    }
}