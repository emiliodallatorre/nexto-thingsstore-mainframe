package com.nextocompany.thingsstore

import java.io.BufferedWriter
import java.io.FileWriter
import java.util.concurrent.Executors

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
    }

    fun initAll() {
        initLogger()
        initControls()
        initListener()
        initWaiter()
        session.logger.log("Server inizializzato correttamente.", References.LEVEL_MESSAGE)
    }
}