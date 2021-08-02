package io.parrotsoftware.pos.common.routes

/**
 * This object contains the routes constants
 */
object Route {
    const val BASE = "/"
    const val HEALTH = "health"

    object Core {
        const val NEW_USER = "user/{user_name}"
        const val NEW_ORDER = "order/{user_name}"
        const val REPORT = "report"
    }

}