package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.handler.ConnectionHandler
import com.nextocompany.thingsstore.session
import java.net.ServerSocket
import java.net.Socket

class ConnectionWaiter : Thread() {
    private val serverSocket: ServerSocket = ServerSocket(References.SERVER_PORT)
    private lateinit var clientSocket: Socket

    override fun run() {
        session.serverStatus = References.STATUS_RUNNING
        session.logger.log("Attivato l'ascolto di nuove connessioni.",
            References.LEVEL_WARNING
        )

        while (session.serverStatus == References.STATUS_RUNNING) {

            try {
                clientSocket = serverSocket.accept()

                session.logger.log(
                    "Connessione in ingresso da: " + clientSocket.inetAddress.hostAddress,
                    References.LEVEL_MESSAGE
                )

                val connectionHandler = ConnectionHandler()
                connectionHandler.clientSocket = clientSocket

                session.listener.executor.execute(connectionHandler)
                session.logger.log("Gestione della connessione inviata a Thread.",
                    References.LEVEL_LOG
                )
            }

            catch (e: java.net.SocketException) {
                if (session.serverStatus != References.STATUS_STOPPED) {
                    session.serverStatus =
                            References.STATUS_ERROR
                    session.logger.log("Il server è stato interrotto senza apparente ragione.",
                        References.LEVEL_ERROR
                    )
                }
            }
        }

        session.logger.log("Disattivato l'ascolto di nuove connessioni.",
            References.LEVEL_WARNING
        )
    }

    override fun interrupt() {
        super.interrupt()
        session.serverStatus = References.STATUS_STOPPED
        session.waiter.serverSocket.close()
    }
}