package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.handler.mysql.LoginManager
import com.nextocompany.thingsstore.session
import java.io.BufferedWriter
import java.io.FileWriter
import java.net.ServerSocket
import java.util.concurrent.Executors

/**
 * Questa classe inizializza la sessione del server.
 * Ogni meccanismo che richieda un utilizzo ripetuto va inizializzato qui.
 * L'obiettivo è di creare il minor numero di nuove istanze possibile per mantenere veloce l'esecuzione.
 */

class ServerInitializer {

    fun initLogger() {
        session.logger = ServerLogger()
        session.logger.bufferedWriter = BufferedWriter(FileWriter(References.FILE_LOG, true))
    }

    fun initControls() {
        session.controls = ServerControls()
    }

    fun initListener() {
        val connectionListener = ConnectionListener()
        connectionListener.executor = Executors.newFixedThreadPool(References.SERVER_MAXCONNECTIONS)
        session.listener = connectionListener
    }

    fun initWaiter() {
        session.waiter = ConnectionWaiter()
        session.waiter.serverSocket = ServerSocket(References.SERVER_PORT)
    }

    fun initWaiter(port: Int) {
        session.waiter = ConnectionWaiter()
        session.waiter.serverSocket = ServerSocket(port)
    }

    fun initLogin() {
        session.login = LoginManager()
        session.login.connect()
    }

    fun initAll() {
        initLogger()
        initControls()
        initListener()
        initWaiter()
        initLogin()
        ServerLogger.log("Server inizializzato correttamente.",
            References.LEVEL_MESSAGE
        )
    }
}