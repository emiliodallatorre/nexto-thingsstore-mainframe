package com.nextocompany.thingsstore

import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ExecutorService

private val serverSocket: ServerSocket = ServerSocket(References.SERVER_PORT)

class ConnectionListener : Thread() {
    lateinit var executor: ExecutorService
    private var clientSocket: Socket = Socket()

    override fun run() {
        session.logger.log("Attivata la funzione di ascolto di nuove connessioni.", References.LEVEL_WARNING)
        acceptConnection.start()

        while (session.serverStatus == References.STATUS_RUNNING) {
            Thread.sleep(References.SERVER_REFRESHRATE)
            println("LOOP")
        }
    }

    override fun interrupt() {
        super.interrupt()
        session.logger.log("Disattivata la funzione di ascolto di nuove connessioni.", References.LEVEL_ERROR)
    }

    private val acceptConnection: Thread = Thread {
        println("Ascolto avviato.")

        while (true) {
            clientSocket = serverSocket.accept()

            session.logger.log(
                "Connessione in ingresso da: " + clientSocket.inetAddress.hostAddress,
                References.LEVEL_MESSAGE
            )

            val connectionHandler = ConnectionHandler()
            connectionHandler.clientSocket = clientSocket

            executor.execute(connectionHandler)
            session.logger.log("Gestione della connessione inviata a Thread.", References.LEVEL_LOG)
            println("Connessione accettata.")
        }
    }
}