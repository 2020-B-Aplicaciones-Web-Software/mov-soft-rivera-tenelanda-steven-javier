package com.example.deber_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class VistaChatSteam : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_chat_steam)

        val jugador_jugando = intent.getParcelableExtra<Jugador>("JugadorJugando")


        if(jugador_jugando!= null){
            findViewById<TextView>(R.id.txview_nickname_jugando_chat).setText(jugador_jugando.nickname)
            findViewById<TextView>(R.id.txview_juego_jugando_chat).setText(jugador_jugando.nombre_juego)
            val url_image = findViewById<ImageView>(R.id.imageView_jugador_jugando_chat)
            Picasso.get().load(jugador_jugando.url_imagen.toString()).into(url_image)
            val chat = arrayListOf<Jugador>()

            val listaJugadoresJugandoChat = arrayListOf<Jugador>()
            listaJugadoresJugandoChat
                .add(
                    Jugador(jugador_jugando.nickname.toString(),"",jugador_jugando.url_imagen.toString(),"Hola como estas")
                )
            listaJugadoresJugandoChat
                .add(
                    Jugador("javier433", "","https://static.wikia.nocookie.net/metalgear/images/9/99/Venom_Snake_TTP.png/revision/latest?cb=20160927033237&path-prefix=es", "Muy bien y tu")
                )
            listaJugadoresJugandoChat
                .add(
                    Jugador(jugador_jugando.nickname.toString(), "",jugador_jugando.url_imagen.toString(), "Quieres jugar Warface conmigo?")
                )


            val recyclerViewChatJugando = findViewById<RecyclerView>(
                R.id.recyclerViewChatJugando
            )
            iniciarRecyclerViewChat(
                listaJugadoresJugandoChat,
                this,
                recyclerViewChatJugando
            )

            val boton_enviar = findViewById<Button>(R.id.btn_envio_mensaje)
            boton_enviar.setOnClickListener{
                val mensaje = findViewById<EditText>(R.id.input_mensaje).text

                listaJugadoresJugandoChat
                    .add(
                        Jugador("javier433", "","https://static.wikia.nocookie.net/metalgear/images/9/99/Venom_Snake_TTP.png/revision/latest?cb=20160927033237&path-prefix=es", mensaje.toString())
                    )
                val recyclerViewChatJugando = findViewById<RecyclerView>(
                    R.id.recyclerViewChatJugando
                )
                iniciarRecyclerViewChat(
                    listaJugadoresJugandoChat,
                    this,
                    recyclerViewChatJugando
                )
                findViewById<EditText>(R.id.input_mensaje).setText("")

            }

            val boton_regresar = findViewById<Button>(R.id.btn_atras)
            boton_regresar.setOnClickListener{
                val intentExplicito = Intent(
                    this,
                    VistaAmigosSteam::class.java
                )
                startActivity(intentExplicito)
            }

        }




    }

    fun iniciarRecyclerViewChat(
        lista: List<Jugador>,
        actividad: VistaChatSteam,
        recyclerView: RecyclerView
    ){
        val adaptador = RecyclerViewChat(
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