package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.ServerSession
import com.nextocompany.thingsstore.session
import java.util.concurrent.ExecutorService

class ConnectionListener {
    lateinit var executor: ExecutorService

    fun startListening() {
        session.waiter.start()
    }

    fun stopListening() {
        session.waiter.interrupt()
        session.waiter.join()
        session.logger.log("Payout del server: " + ServerSession.listener.executor,
            References.LEVEL_LOG
        )
        session.initializer.initWaiter()
    }
}