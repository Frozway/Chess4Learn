package fr.thibaut.chess

import fr.thibaut.chess.Env.setEnvName
import fr.thibaut.chess.utils.ConsoleManager.clearScreen
import fr.thibaut.chess.utils.DatabaseManager.launchDatabase
import io.github.oshai.kotlinlogging.KotlinLogging

object GameLaunch {

    private val logger = KotlinLogging.logger {}

    @JvmStatic
    fun main(args: Array<String>) {

        setEnvName(args.get(0))
        logger.info { "Environment set to: ${Env.name}" }
        logger.info { "Launching the game..."}

        launchDatabase()

        clearScreen()

        println("Welcome to the chess game!")

    }

}