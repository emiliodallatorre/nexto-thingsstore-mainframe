package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.ServerSession
import com.nextocompany.thingsstore.session
import java.util.concurrent.ExecutorService

/**
 * Questa classe è una classe di supporto.
 * Serve solamente a gestire, dal di fuori di un thread, il ciclo di vita di ConnectionWaiter.
 * I due metodi consentono di fermare o attivare l'ascolto di nuove connessioni.
 */

class ConnectionListener {
    lateinit var executor: ExecutorService

    fun startListening() {
        session.waiter.start()
    }

    fun stopListening() {
        session.waiter.interrupt()
        session.waiter.join()
        ServerLogger.log("Payout del server: " + ServerSession.listener.executor,
            References.LEVEL_LOG
        )
        session.initializer.initWaiter()
    }
}