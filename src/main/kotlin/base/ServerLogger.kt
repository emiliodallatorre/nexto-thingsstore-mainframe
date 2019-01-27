package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session
import java.io.BufferedWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Questa classe si occupa di scrivere il log del server.
 * Tutto il log viene salvato anche su un file, che funge da registro.
 */

class ServerLogger {
    lateinit var bufferedWriter: BufferedWriter

    /**
     * La funzione log stampa a schermo e su file i messaggi del server.
     * I colori sono definiti tramite la codifica ANSI, e passati come referenze.
     */

    companion object {
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

            if (level != References.LEVEL_SERVER) logToFile(("$dateStamp - $timeStamp | $message").replace("\n", ""))

            if (level != References.LEVEL_FILE) logToScreen(
                color + "$timeStamp | $message" + References.ANSI_RESET
            )
        }

        private fun logToScreen(message: String) {
            println(message)
        }

        private fun logToFile(message: String) {
            session.logger.bufferedWriter.write(message)
            session.logger.bufferedWriter.newLine()
            session.logger.bufferedWriter.flush()
        }
    }
}