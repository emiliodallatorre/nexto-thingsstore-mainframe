package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.ServerSession
import com.nextocompany.thingsstore.handler.ConnectionHandler
import java.net.ServerSocket
import java.net.Socket

class ConnectionWaiter : Thread() {
    private val serverSocket: ServerSocket = ServerSocket(References.SERVER_PORT)
    private lateinit var clientSocket: Socket

    override fun run() {
        ServerSession.serverStatus = References.STATUS_RUNNING
        ServerSession.logger.log("Attivato l'ascolto di nuove connessioni.",
            References.LEVEL_WARNING
        )

        while (ServerSession.serverStatus == References.STATUS_RUNNING) {

            try {
                clientSocket = serverSocket.accept()

                ServerSession.logger.log(
                    "Connessione in ingresso da: " + clientSocket.inetAddress.hostAddress,
                    References.LEVEL_MESSAGE
                )

                val connectionHandler = ConnectionHandler()
                connectionHandler.clientSocket = clientSocket

                ServerSession.listener.executor.execute(connectionHandler)
                ServerSession.logger.log("Gestione della connessione inviata a Thread.",
                    References.LEVEL_LOG
                )
            }

            catch (e: java.net.SocketException) {
                if (ServerSession.serverStatus != References.STATUS_STOPPED) {
                    ServerSession.serverStatus =
                            References.STATUS_ERROR
                    ServerSession.logger.log("Il server è stato interrotto senza apparente ragione.",
                        References.LEVEL_ERROR
                    )
                }
            }
        }

        ServerSession.logger.log("Disattivato l'ascolto di nuove connessioni.",
            References.LEVEL_WARNING
        )
    }

    override fun interrupt() {
        super.interrupt()
        ServerSession.serverStatus = References.STATUS_STOPPED
        ServerSession.waiter.serverSocket.close()
    }
}