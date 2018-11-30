package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.ServerSession
import java.io.BufferedWriter
import java.io.FileWriter
import java.util.concurrent.Executors

class ServerInitializer {

    fun initLogger() {
        ServerSession.logger = ServerLogger()
        ServerSession.logger.bufferedWriter = BufferedWriter(FileWriter(References.FILE_LOG, true))
    }

    fun initControls() {
        ServerSession.controls = ServerControls()
    }

    fun initListener() {
        val connectionListener = ConnectionListener()
        connectionListener.executor = Executors.newFixedThreadPool(References.SERVER_MAXCONNECTIONS)
        ServerSession.listener = connectionListener
    }

    fun initWaiter() {
        ServerSession.waiter = ConnectionWaiter()
    }

    fun initAll() {
        initLogger()
        initControls()
        initListener()
        initWaiter()
        ServerSession.logger.log("Server inizializzato correttamente.",
            References.LEVEL_MESSAGE
        )
    }
}