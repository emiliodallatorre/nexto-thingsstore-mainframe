package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session
import com.nextocompany.thingsstore.test.ServerTest
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

    fun initTester() {
        session.tester = ServerTest()
    }

    fun initAll() {
        initLogger()
        initControls()
        initListener()
        initWaiter()
        initTester()
        session.logger.log("Server inizializzato correttamente.",
            References.LEVEL_MESSAGE
        )
    }
}