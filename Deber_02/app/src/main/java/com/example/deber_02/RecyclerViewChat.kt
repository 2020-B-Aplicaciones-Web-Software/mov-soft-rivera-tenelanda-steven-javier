package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewChat (
    private val contexto: VistaChatSteam,
    private val listaJugador: List<Jugador>,
    private val recyclerView: RecyclerView,
) : RecyclerView.Adapter<RecyclerViewChat.MyViewHolder>(){
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nicknameTextView: TextView
        val mensajeTextView: TextView
        val url_imagen: ImageView

        init {
            nicknameTextView = view.findViewById(R.id.txview_nickname_jugando_mensaje)
            mensajeTextView = view.findViewById(R.id.txview_mensaje)
            url_imagen = view.findViewById(R.id.imageView_jugador_jugando_mensaje)
        }
    }
    //Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_chat,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    //Setear los datos de cada iteracion del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val jugador = listaJugador[position]
        holder.nicknameTextView.text = jugador.nickname
        holder.mensajeTextView.text = jugador.mensaje
        Picasso.get().load(jugador.url_imagen.toString()).into(holder.url_imagen)
    }

    override fun getItemCount(): Int {
        return listaJugador.size
    }
}