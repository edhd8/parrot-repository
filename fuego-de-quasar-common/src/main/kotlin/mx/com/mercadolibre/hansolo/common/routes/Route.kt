package mx.com.mercadolibre.hansolo.common.routes

/**
 * This object contains the routes constants
 */
object Route {
    const val BASE = "/"
    const val HEALTH = "health"

    object Resistance {
        const val TOP_SECRET = "topsecret"
        const val TOP_SECRET_SPLIT = "topsecret_split/{satellite_name}"
        const val LOCATION = "location"
        const val MESSAGE = "message"
    }

}