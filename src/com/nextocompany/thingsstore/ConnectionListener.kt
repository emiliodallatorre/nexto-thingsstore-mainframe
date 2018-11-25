package com.nextocompany.thingsstore

import java.util.concurrent.ExecutorService

class ConnectionListener {
    lateinit var executor: ExecutorService

    fun startListening() {
        session.waiter.start()
    }

    fun stopListening() {
        session.waiter.interrupt()
        session.waiter.join()
        session.logger.log("Payout del server: " + session.listener.executor, References.LEVEL_LOG)
        session.initializer.initWaiter()
    }
}