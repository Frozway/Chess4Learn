package fr.thibaut.chess.repository

import fr.thibaut.chess.models.Games
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object GameRepository {

    private val logger = KotlinLogging.logger {}

    fun setupGame(whitePlayerId: Int, blackPlayerId: Int) {
        try {
            transaction {
                Games.insert {
                    it[whitePlayer] = whitePlayerId
                    it[blackPlayer] = blackPlayerId
                    it[this.result] = "In progress"
                    it[this.nbMoves] = 0
                    it[this.pgn] = ""
                }
            }

            logger.info { "Game created" }
        }
        catch (e: Exception) {
            logger.error { "Error while creating a game: $e" }
        }
    }

    fun updateGame(gameId: Int, result: String, nbMoves: Int, pgn: String) {
        try {
            transaction {
                Games.update({ Games.id eq gameId }) {
                    it[this.result] = result
                    it[this.nbMoves] = nbMoves
                    it[this.pgn] = pgn
                }
            }
            logger.info { "Game updated" }
        }
        catch (e: Exception) {
            logger.error { "Error while updating a game: $e" }
        }
    }
}