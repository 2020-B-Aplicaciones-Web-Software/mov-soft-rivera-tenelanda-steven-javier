package com.example.deber_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class VistaChatSteamOnline : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_chat_steam_online)

        val jugador_online = intent.getParcelableExtra<Jugador>("JugadorOnline")

        if(jugador_online!= null){
            findViewById<TextView>(R.id.txview_nickname_online_chat).setText(jugador_online.nickname)
            findViewById<TextView>(R.id.txview_juego_online_chat).setText(jugador_online.nombre_juego)
            val url_image = findViewById<ImageView>(R.id.imageView_jugador_online_chat)
            Picasso.get().load(jugador_online.url_imagen.toString()).into(url_image)
            val chat = arrayListOf<Jugador>()

            val listaJugadoresOnlineChat = arrayListOf<Jugador>()
            listaJugadoresOnlineChat
                .add(
                    Jugador(jugador_online.nickname.toString(),"",jugador_online.url_imagen.toString(),"Hola como estas")
                )
            listaJugadoresOnlineChat
                .add(
                    Jugador("javier433", "","https://static.wikia.nocookie.net/metalgear/images/9/99/Venom_Snake_TTP.png/revision/latest?cb=20160927033237&path-prefix=es", "Muy bien y tu")
                )
            listaJugadoresOnlineChat
                .add(
                    Jugador(jugador_online.nickname.toString(), "",jugador_online.url_imagen.toString(), "Veo que no estas jugando nada")
                )
            listaJugadoresOnlineChat
                .add(
                    Jugador(jugador_online.nickname.toString(), "",jugador_online.url_imagen.toString(), "Quieres jugar Dead By Daylight?")
                )

            val recyclerViewChatOnline = findViewById<RecyclerView>(
                R.id.recyclerViewChatOnline
            )
            iniciarRecyclerViewChatOnline(
                listaJugadoresOnlineChat,
                this,
                recyclerViewChatOnline
            )

            val boton_enviar_online = findViewById<Button>(R.id.btn_envio_mensaje_online)
            boton_enviar_online.setOnClickListener{
                val mensaje = findViewById<EditText>(R.id.input_mensaje_online).text

                listaJugadoresOnlineChat
                    .add(
                        Jugador("javier433", "","https://static.wikia.nocookie.net/metalgear/images/9/99/Venom_Snake_TTP.png/revision/latest?cb=20160927033237&path-prefix=es", mensaje.toString())
                    )
                val recyclerViewChatOnline = findViewById<RecyclerView>(
                    R.id.recyclerViewChatOnline
                )
                iniciarRecyclerViewChatOnline(
                    listaJugadoresOnlineChat,
                    this,
                    recyclerViewChatOnline
                )
                findViewById<EditText>(R.id.input_mensaje_online).setText("")

            }
            val boton_regresar_online = findViewById<Button>(R.id.btn_atras_online)
            boton_regresar_online.setOnClickListener{
                val intentExplicito = Intent(
                    this,
                    VistaAmigosSteam::class.java
                )
                startActivity(intentExplicito)
            }

        }
    }

    fun iniciarRecyclerViewChatOnline(
        lista: List<Jugador>,
        actividad: VistaChatSteamOnline,
        recyclerView: RecyclerView
    ){
        val adaptador = RecyclerViewChatOnline(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }
}