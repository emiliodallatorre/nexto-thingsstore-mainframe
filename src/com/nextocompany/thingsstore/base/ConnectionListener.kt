package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.ServerSession
import java.util.concurrent.ExecutorService

class ConnectionListener {
    lateinit var executor: ExecutorService

    fun startListening() {
        ServerSession.waiter.start()
    }

    fun stopListening() {
        ServerSession.waiter.interrupt()
        ServerSession.waiter.join()
        ServerSession.logger.log("Payout del server: " + ServerSession.listener.executor,
            References.LEVEL_LOG
        )
        ServerSession.initializer.initWaiter()
    }
}