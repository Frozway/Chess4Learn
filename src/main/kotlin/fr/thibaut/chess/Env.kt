package fr.thibaut.chess

import java.util.*

object Env {

    var name: String = "DEV"

    fun setEnvName(name: String) {
        this.name = name.uppercase()
    }

    val databasePath: String = "./src/main/resources/database.db"
}