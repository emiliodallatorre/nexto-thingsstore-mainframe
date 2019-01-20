package com.nextocompany.thingsstore.handler

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.EOFException
import java.net.Socket

/**
 * Questa è una delle classi cruciali del server.
 * Il suo scopo è quello di gestire le richieste del client e fornire le giuste risposte.
 * Le funzioni che gestiscono le risposte da mandare al client sono in ServerFunctions().
 */

class ConnectionHandler : Thread() {
    private val functions: ServerFunctions = ServerFunctions()
    lateinit var clientSocket: Socket
    private lateinit var input: DataInputStream
    private lateinit var output: DataOutputStream

    override fun run() {
        ping(
            "Connessione in ingresso da: " + clientSocket.inetAddress.hostAddress,
            References.LEVEL_MESSAGE
        )

        startHandling()
    }

    private fun startHandling() {
        input = DataInputStream(clientSocket.getInputStream())

        output = DataOutputStream(clientSocket.getOutputStream())

        try {
            when (input.readByte().toInt()) {
                References.CODE_TEST -> functions.test(input, output)
                References.CODE_LOGIN -> functions.validateLogin(input, output)

                else -> ping("Tentativo di accesso non riconosciuto.", References.LEVEL_ERROR)
            }

            stopHandling()
        }

        // Qui sappiamo che abbiamo raggiunto la fine dello stream in entrata, non è un problema.
        catch (e: EOFException) {
            ping("Qualcosa non funziona.", References.LEVEL_ERROR)
        }
    }

    private fun stopHandling() {
        clientSocket.close()
        ping(
            "Disconnessione da: " + clientSocket.inetAddress.hostAddress,
            References.LEVEL_LOG
        )
    }

    fun ping(message: String, level: Int) {
        // Qui viene aggiunto il prefisso del thread all'output del log.
        session.logger.log("[ " + String.format("%03d", Thread.currentThread().id) + " ] $message", level)
    }
}