package com.nextocompany.thingsstore

import java.io.BufferedWriter
import java.text.SimpleDateFormat
import java.util.*

class ServerLogger {
    lateinit var bufferedWriter: BufferedWriter

    fun log(message: String, level: Int) {
        val dateStamp: String = SimpleDateFormat("dd/MM/yyyy").format(Date())
        val timeStamp: String = SimpleDateFormat("HH:mm:ss").format(Date())

        lateinit var ANSI_COLOR: String

        when (level) {
            References.LEVEL_ERROR -> ANSI_COLOR = References.ANSI_RED
            References.LEVEL_SERVER -> ANSI_COLOR = References.ANSI_CYAN
            References.LEVEL_WARNING -> ANSI_COLOR = References.ANSI_YELLOW
            References.LEVEL_MESSAGE -> ANSI_COLOR = References.ANSI_GREEN
            References.LEVEL_LOG -> ANSI_COLOR = References.ANSI_WHITE
        }

        logToFile(("$dateStamp - $timeStamp | $message").replace("\n", ""), level)

        if (level != References.LEVEL_FILE) logToScreen(
            ANSI_COLOR + "$timeStamp | $message" + References.ANSI_RESET,
            level
        )
    }

    private fun logToScreen(message: String, level: Int) {
        if (session.verboseOutput || level < 4) println(message)
    }

    private fun logToFile(message: String, level: Int) {
        var messageFormatted: String = message
        if(level == References.LEVEL_FILE) messageFormatted = "$message"
        session.logger.bufferedWriter.write(messageFormatted)
        session.logger.bufferedWriter.newLine()
        session.logger.bufferedWriter.flush()
    }
}