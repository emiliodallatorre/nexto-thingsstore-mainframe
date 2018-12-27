package com.nextocompany.thingsstore.handler

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session
import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.Socket

class ConnectionHandler : Thread() {
    lateinit var clientSocket: Socket
    lateinit var input: DataInputStream
    lateinit var output: DataOutputStream

    override fun run() {
        startHandling()
        stopHandling()
    }

    private fun startHandling() {
        input = DataInputStream(clientSocket.getInputStream())
        output = DataOutputStream(clientSocket.getOutputStream())

        while (true) {
            val a = input.readByte().toInt()

            when (a) {
                References.CODE_TEST -> {
                    if (input.readUTF() == References.CODE_TEST_STRING) {
                        ping("Connessione confermata", References.LEVEL_LOG)
                        output.writeByte(References.CODE_TEST)
                        output.writeUTF(References.CODE_TEST_STRING)
                        output.flush()
                    }
                }
                else -> {
                }
            }
        }
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