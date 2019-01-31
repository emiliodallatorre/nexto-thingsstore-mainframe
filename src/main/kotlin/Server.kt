package com.nextocompany.thingsstore

import com.nextocompany.thingsstore.base.ServerControls
import com.nextocompany.thingsstore.base.ServerInitializer
import com.nextocompany.thingsstore.base.ServerLogger

val session: ServerSession = ServerSession

var test: Boolean = false
var working: Boolean = false

fun main() {
    session.initializer = ServerInitializer()

    session.initializer.initAll()

    session.listener.startListening()

    while (!test) {
        when (readLine()!!) {
            "stop" -> ServerControls.stop()
            "start" -> ServerControls.start()
            "verbose" -> ServerControls.verbose()
            "status" -> ServerControls.status()
            "disconnetti" -> ServerControls.disconnectAll()
            "log" -> ServerControls.log()
            "test" -> ServerControls.test()
            else -> ServerLogger.log("Errore, riprovare!\n", References.LEVEL_SERVER)
        }
    }

    working = true
}