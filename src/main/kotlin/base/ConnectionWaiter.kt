package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.handler.ConnectionHandler
import com.nextocompany.thingsstore.session
import java.net.ServerSocket
import java.net.Socket

/**
 * Questa classe si occupa di aspettare che un client si connetta.
 * Nel momento in cui un utente si connette, l'oggetto socket viene mandato a ConnectionHandler.
 * Lo scopo di questa classe è quello di smistare, di fatto, le connessioni in entrata.
 * Il metodo interrupt consente di fermare l'ascolto di nuove connessioni.
 */

class ConnectionWaiter : Thread() {
    lateinit var serverSocket: ServerSocket
    private lateinit var clientSocket: Socket

    override fun run() {
        session.serverStatus = References.STATUS_RUNNING
        ServerLogger.log("Attivato l'ascolto di nuove connessioni.",
            References.LEVEL_WARNING
        )

        while (session.serverStatus == References.STATUS_RUNNING) {

            try {
                session.serverStatus = References.STATUS_RUNNING
                ServerLogger.log("Aspettando per la connessione...",
                    References.LEVEL_LOG
                )

                clientSocket = serverSocket.accept()

                val connectionHandler = ConnectionHandler()
                connectionHandler.clientSocket = clientSocket

                session.listener.executor.execute(connectionHandler)
            }

            catch (e: java.net.SocketException) {
                if (session.serverStatus != References.STATUS_STOPPED) {
                    session.serverStatus =
                            References.STATUS_ERROR
                    ServerLogger.log("Il server è stato interrotto senza apparente ragione.",
                        References.LEVEL_ERROR
                    )
                }
            }
        }

        ServerLogger.log("Disattivato l'ascolto di nuove connessioni.",
            References.LEVEL_WARNING
        )
    }

    override fun interrupt() {
        super.interrupt()
        session.serverStatus = References.STATUS_STOPPED
        session.waiter.serverSocket.close()
    }
}