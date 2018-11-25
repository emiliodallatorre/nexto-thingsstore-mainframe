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

        // Scrive su un file tutto ciò che viene stampato sullo schermo.
        session.logger.bufferedWriter.write(("$time | $message").replace("\n", ""))
        session.logger.bufferedWriter.newLine()
        session.logger.bufferedWriter.flush()

        if(session.verboseOutput) println(ANSI_COLOR + "$time | $message" + References.ANSI_RESET)
        else if(level < 4) println(ANSI_COLOR + "$time | $message" + References.ANSI_RESET)
    }
}