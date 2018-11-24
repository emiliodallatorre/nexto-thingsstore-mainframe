package com.nextocompany.thingsstore

import java.net.Socket

class ConnectionListener {
    fun startListening() {

        val connectionListener = Thread {
            while (session.serverStatus == References.STATUS_RUNNING) {
                val clientSocket: Socket = serverSocket.accept()

                session.logger.log(
                    "\nConnessione in ingresso da: " + clientSocket.inetAddress.hostAddress,
                    References.LEVEL_MESSAGE
                )

                val connectionHandler = ConnectionHandler()
                connectionHandler.clientSocket = clientSocket

                executor.execute(connectionHandler)

                session.logger.log("Gestione della connessione inviata a Thread.", References.LEVEL_LOG)
            }
        }
        connectionListener.start()
    }
}