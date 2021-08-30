package com.example.deber_02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class VistaAmigosSteam : AppCompatActivity() {
    var ArrayJugadoresJugando = ArrayList<Jugador>()
    var ArrayJugadoresOnline = ArrayList<Jugador>()
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vista_amigos_steam)

        val listaJugadoresJugando = arrayListOf<Jugador>()
        listaJugadoresJugando
            .add(
                Jugador("steven118","FIFA 20","https://static.wikia.nocookie.net/doblaje/images/2/25/Mario_New_Super_Mario_Bros_U_Deluxe.png/revision/latest/smart/width/250/height/250?cb=20190208044143&path-prefix=es","Hola como estas")
            )
        listaJugadoresJugando
            .add(
                Jugador("Stan123", "MORDHAU","https://esports.as.com/2019/07/04/fighting-games/personajes-locos-juegos-lucha_1260483957_227275_1024x576.jpg", "Quieres jugar")
            )
        listaJugadoresJugando
            .add(
                Jugador("Damvers", "Dead by Daylight","https://esports.as.com/2019/11/28/bonus/Nombres-personajes-videojuegos-usados-vida_1304579628_310027_1024x576.jpg", "Quieres jugar")
            )
        listaJugadoresJugando
            .add(
                Jugador("garet202", "Black Desert","https://www.losreplicantes.com/images/articulos/2000/2196/8.jpg", "Quieres jugar")
            )
        listaJugadoresJugando
            .add(
                Jugador("Erick1809", "The Witcher 3","https://static.alfabetajuega.com/abj_public_files/multimedia/imagenes/201608/158924.alfabetajuega-sonic-080816.jpg", "Quieres jugar")
            )

        val recyclerViewJugando = findViewById<RecyclerView>(
            R.id.recycler_view_jugando
        )
        ArrayJugadoresJugando=listaJugadoresJugando

        iniciarRecyclerViewJugando(
            listaJugadoresJugando,
            this,
            recyclerViewJugando
        )

        val listaJugadoresOnline = arrayListOf<Jugador>()
        listaJugadoresOnline
            .add(
                Jugador("andres442","Online","https://sm.ign.com/ign_es/screenshot/default/samus-aran-by-magicnaanavi-d6vbe9s_5ct4.jpg","Veo que no estas jugado,\nQuieres jugar?")
            )
        listaJugadoresOnline
            .add(
                Jugador("Zoey654", "Online","https://www.pngkey.com/png/detail/738-7383719_resultado-de-imagen-para-personajes-de-videojuegos.png","Hola quieres jugar")
            )
        listaJugadoresOnline
            .add(
                Jugador("Maniac", "Online","https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/1200/public/media/image/2013/07/234207-juegos-lucha-olvido.jpg?itok=1p_4pzxv","Hola quieres jugar")
            )
        listaJugadoresOnline
            .add(
                Jugador("PedJess", "Online","https://cdn.pocket-lint.com/r/s/320x/assets/images/131918-games-news-buyer-s-guide-the-best-game-characters-that-defined-25-years-of-playstation-image3-tdkx76glhh.jpg?v1","Hola quieres jugar")
            )
        listaJugadoresOnline
            .add(
                Jugador("ROKECS", "Online","https://cdn.pocket-lint.com/r/s/320x/assets/images/131918-games-news-buyer-s-guide-the-best-game-characters-that-defined-25-years-of-playstation-image9-oq7ta3mw56.jpg?v1","Hola quieres jugar")
            )
        val recyclerViewOnline = findViewById<RecyclerView>(
            R.id.recycler_view_online
        )

        ArrayJugadoresOnline=listaJugadoresOnline
        iniciarRecyclerViewOnline(
            listaJugadoresOnline,
            this,
            recyclerViewOnline
        )

        //obtener la posicion del elemento en el recycler view
        val adapter = RecyclerViewAmigosJugando(this,listaJugadoresJugando,recyclerViewJugando
            )
        recyclerViewJugando.adapter = adapter
        adapter.setOnItemClickListener(object : RecyclerViewAmigosJugando.onItemClickListener {
            override fun onItemClick(position: Int) {
                IrChatSteamJugando(position)
            }

        })

        val adapter2 = RecyclerViewAmigosOnline(this,listaJugadoresOnline,recyclerViewOnline
        )
        recyclerViewOnline.adapter = adapter2
        adapter2.setOnItemClickListener(object : RecyclerViewAmigosOnline.onItemClickListener {
            override fun onItemClick(position: Int) {
                IrChatSteamOnline(position)
            }

        })


    }

    fun IrChatSteamJugando(posicion: Int) {
        val IrChatSteamForm = Intent(
            this,
            VistaChatSteam::class.java
        )
        IrChatSteamForm.putExtra("JugadorJugando", ArrayJugadoresJugando[posicion])
        startActivityForResult(IrChatSteamForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun IrChatSteamOnline(posicion: Int) {
        val IrChatSteamForm = Intent(
            this,
            VistaChatSteamOnline::class.java
        )
        IrChatSteamForm.putExtra("JugadorOnline", ArrayJugadoresOnline[posicion])
        startActivityForResult(IrChatSteamForm, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    fun iniciarRecyclerViewJugando(
        lista: List<Jugador>,
        actividad: VistaAmigosSteam,
        recyclerView: RecyclerView
    ){
        val adaptador = RecyclerViewAmigosJugando(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()

    }

    fun iniciarRecyclerViewOnline(
        lista: List<Jugador>,
        actividad: VistaAmigosSteam,
        recyclerView: RecyclerView
    ){
        val adaptador = RecyclerViewAmigosOnline(
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
