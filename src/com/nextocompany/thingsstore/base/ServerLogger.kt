package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.ServerSession
import java.io.BufferedWriter
import java.text.SimpleDateFormat
import java.util.*

class ServerLogger {
    lateinit var bufferedWriter: BufferedWriter

    fun log(message: String, level: Int) {
        val dateStamp: String = SimpleDateFormat("dd/MM/yyyy").format(Date())
        val timeStamp: String = SimpleDateFormat("HH:mm:ss").format(Date())

        lateinit var color: String

        when (level) {
            References.LEVEL_ERROR -> color =
                    References.ANSI_RED
            References.LEVEL_SERVER -> color =
                    References.ANSI_CYAN
            References.LEVEL_WARNING -> color =
                    References.ANSI_YELLOW
            References.LEVEL_MESSAGE -> color =
                    References.ANSI_GREEN
            References.LEVEL_LOG -> color =
                    References.ANSI_WHITE
        }

        logToFile(("$dateStamp - $timeStamp | $message").replace("\n", ""), level)

        if (level != References.LEVEL_FILE) logToScreen(
            color + "$timeStamp | $message" + References.ANSI_RESET,
            level
        )
    }

    private fun logToScreen(message: String, level: Int) {
        if (ServerSession.verboseOutput || level < 4) println(message)
    }

    private fun logToFile(message: String, level: Int) {
        if (level != References.LEVEL_MESSAGE) {
            ServerSession.logger.bufferedWriter.write(message)
            ServerSession.logger.bufferedWriter.newLine()
            ServerSession.logger.bufferedWriter.flush()
        }
    }
}