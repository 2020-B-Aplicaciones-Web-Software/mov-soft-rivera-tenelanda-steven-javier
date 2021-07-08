package com.example.moviles_software_2021_a

class BBaseDatosMemoria {

    companion object {
        //Tenemos propiedades y metodos estaticos de la clase
        //Propiedades
        //Metodos
        //Estaticos que estan guardadas en un(Singleton)
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        //aqui se prenseta lo que se necesita inicializar
        init {
            arregloBEntrenador
                .add(
                    BEntrenador("Adrian","a@a.com", null)
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Vicente","b@b.com", null)
                )
            arregloBEntrenador
                .add(
                    BEntrenador("Carolina","c@c.com",null)
                )
        }

    }
}

//no se necesita crear instancias para acceder al companion object de cualquier clase
    //fun a(){
//      BBaseDatosMemoria.arregloBEntrenador
// }

