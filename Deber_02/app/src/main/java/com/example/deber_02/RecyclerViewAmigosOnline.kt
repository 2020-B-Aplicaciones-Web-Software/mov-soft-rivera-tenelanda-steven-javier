package com.example.deber_02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
private lateinit var mListener: RecyclerViewAmigosOnline.onItemClickListener
class RecyclerViewAmigosOnline (
    private val contexto: VistaAmigosSteam,
    private val listaJugador: List<Jugador>,
    private val recyclerView: RecyclerView,
) : RecyclerView.Adapter<RecyclerViewAmigosOnline.MyViewHolder>(){

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    inner class MyViewHolder(view: View, listener: onItemClickListener) : RecyclerView.ViewHolder(view){
        val nicknameTextView: TextView
        val juegoTextView: TextView
        val url_imagen: ImageView

        init {
            nicknameTextView = view.findViewById(R.id.txview_nickname_online)
            juegoTextView = view.findViewById(R.id.txview_juego_online)
            url_imagen = view.findViewById(R.id.imageView_jugador_online)
            view.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
    //Setear el layout que vamos a utilizar
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_jugadores_online,
                parent,
                false
            )
        return MyViewHolder(itemView, mListener)
    }

    //Setear los datos de cada iteracion del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val jugador = listaJugador[position]
        holder.nicknameTextView.text = jugador.nickname
        holder.juegoTextView.text = jugador.nombre_juego
        Picasso.get().load(jugador.url_imagen.toString()).into(holder.url_imagen)
    }

    override fun getItemCount(): Int {
        return listaJugador.size
    }

}