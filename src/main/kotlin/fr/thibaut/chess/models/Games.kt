package fr.thibaut.chess.models

import org.jetbrains.exposed.dao.id.IntIdTable

object Games : IntIdTable() {
    val whitePlayer = reference("whitePlayer", Players)
    val blackPlayer = reference("blackPlayer", Players)
    val result = varchar("result", 10)
    val nbMoves = integer("moves")
    val pgn = text("pgn")
}