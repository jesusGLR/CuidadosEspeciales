package com.example.proyecto10

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_register_pacient.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(private val context:Context):RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    //lista vacia
    private var dataList = mutableListOf<Paciente>()

    fun setListaData(data:MutableList<Paciente>){
        dataList = data
    }
    //mostrar vistas en el recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false)
        return MainViewHolder(view)
    }
    override fun getItemCount(): Int {

         return if(dataList.size>0){
          return dataList.size
         }else{
                 0
            }

    }



    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val paciente:Paciente = dataList[position]
        holder.bindView(paciente)

    }


    //viewgolder para recycler view
    inner class MainViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        //adaptador de tipo usuario
        fun bindView(paciente: Paciente){
            Glide.with(context).load(paciente.imageUrl).into(itemView.circlImageView)

            itemView.text_title.text = paciente.nombre
            itemView.txt_Enfermedad.text = paciente.padecimiento
        }
    }
}