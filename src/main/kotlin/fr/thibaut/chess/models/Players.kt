package fr.thibaut.chess.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Players : IntIdTable(){
    val name = varchar("name", 50)
    val elo = integer("elo")
    val country = varchar("country", 50)
}