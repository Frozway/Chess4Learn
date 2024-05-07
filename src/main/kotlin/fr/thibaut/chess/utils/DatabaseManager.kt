package fr.thibaut.chess.utils

import fr.thibaut.chess.Env
import fr.thibaut.chess.models.Games
import fr.thibaut.chess.models.Players
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseManager {

    private val logger = KotlinLogging.logger {}

    fun launchDatabase() {
        try {
            Database.connect("jdbc:sqlite:${Env.databasePath}", "org.sqlite.JDBC")
            logger.info { "Connected to the database" }

            createSchemas()

        } catch (e: Exception) {
            logger.error { "Error while connecting to the database: $e" }
        }
    }

    fun createSchemas() {
        try {
            transaction {
                // Create tables if they don't exist
                SchemaUtils.create(Players, Games)
            }
        } catch (e: Exception) {
            logger.error { "Error while creating tables: $e" }
        }
    }
}