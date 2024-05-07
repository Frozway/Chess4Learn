package fr.thibaut.chess.repository

import fr.thibaut.chess.models.Players
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random

object PlayerRepository {

    private val logger = KotlinLogging.logger {}

    private fun generateGuestName(): String {
        val randomSuffix = Random.nextInt(1000)
        return if (isPlayerNameExists("Guest-$randomSuffix")) generateGuestName()
        else "Guest-$randomSuffix"
    }

    private fun isPlayerNameExists(name: String): Boolean {
        return transaction {
            Players.selectAll().where { Players.name eq name }.count() > 0
        }
    }

    fun setupPlayer(): Int {
        println("Enter player details:")

        print("Name: ")
        val name = readlnOrNull() ?: generateGuestName()

        print("Elo: ")
        val elo = readlnOrNull()?.toInt() ?: 1000

        print("Country: ")
        val country = readlnOrNull() ?: "No country specified"

        // Insert player into database
        try {
            val playerId = transaction {
                Players.insertAndGetId {
                    it[Players.name] = name
                    it[Players.elo] = elo
                    it[Players.country] = country
                }
            }
            logger.info { "Player added with ID: $playerId" }
            println("Player added successfully.")
            return playerId.value
        }
        catch (e: Exception) {
            logger.error { "Error while adding player: $e" }
            println("Error while adding player.")
            return -1
        }
    }

}