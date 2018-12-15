package com.nextocompany.thingsstore.handler

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.Socket

class ConnectionHandler : Thread() {
    lateinit var clientSocket: Socket
    lateinit var input: InputStreamReader
    lateinit var output: PrintStream

    override fun run() {
        startHandling()

        output.print("Ciao, l\'input va!")
        output.flush()

        ping(input.read().toString(), References.LEVEL_LOG)

        stopHandling()
    }

    private fun startHandling() {
        input = InputStreamReader(clientSocket.getInputStream())
        output = PrintStream(clientSocket.getOutputStream())

        ping(
            "Avvio della gestione di: " + clientSocket.inetAddress.hostAddress,
            References.LEVEL_LOG
        )
    }

    private fun stopHandling() {
        clientSocket.close()
        ping(
            "Disconnessione da: " + clientSocket.inetAddress.hostAddress,
            References.LEVEL_LOG
        )
    }

    private fun ping(message: String, level: Int) {
        // Qui viene aggiunto il prefisso del thread all'output del log.
        session.logger.log("[ " + String.format("%03d", Thread.currentThread().id) + " ] $message", level)
    }
}