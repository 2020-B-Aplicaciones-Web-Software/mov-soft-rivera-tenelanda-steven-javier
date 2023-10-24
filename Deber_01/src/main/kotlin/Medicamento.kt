class Medicamento(
    private var identificador: Int,
    private var nombre: String,
    private var concentracion: Double,
    private var via_administracion: String,
    private var forma_farmaceutica: String,
    private var venta_libre: Boolean,
) {
    init {

    }

    //GETTERS
    fun getIdentificador(): Int {
        return identificador
    }

    fun getNombre(): String {
        return nombre
    }

    fun getConcentracion(): Double {
        return concentracion
    }

    fun getVia_administracion(): String {
        return via_administracion
    }

    fun getForma_farmaceutica(): String {
        return forma_farmaceutica
    }

    fun getVenta_libre(): Boolean {
        return venta_libre
    }

    //SETTERS

    fun setIdentificador(identificador: Int) {
        this.identificador = identificador
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun setConcentracion(concentracion: Double) {
        this.concentracion = concentracion
    }

    fun setVia_administracion(via_administracion: String) {
        this.via_administracion = via_administracion
    }

    fun setForma_farmaceutica(forma_farmaceutica: String) {
        this.forma_farmaceutica = forma_farmaceutica
    }

    fun setVenta_libre(venta_libre: Boolean) {
        this.venta_libre = venta_libre
    }
}