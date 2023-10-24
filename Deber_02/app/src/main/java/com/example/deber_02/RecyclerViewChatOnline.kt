package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerViewChatOnline (
    private val contexto: VistaChatSteamOnline,
    private val listaJugador: List<Jugador>,
    private val recyclerView: RecyclerView,
): RecyclerView.Adapter<RecyclerViewChatOnline.MyViewHolder>(){
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nicknameTextView: TextView
        val mensajeTextView: TextView
        val url_imagen: ImageView

        init {
            nicknameTextView = view.findViewById(R.id.txview_nickname_online_mensaje)
            mensajeTextView = view.findViewById(R.id.txview_mensaje_online)
            url_imagen = view.findViewById(R.id.imageView_jugador_online_mensaje)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_chat_online,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

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