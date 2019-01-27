package com.nextocompany.thingsstore

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
            "stop" -> session.controls.stop()
            "start" -> session.controls.start()
            "verbose" -> session.controls.verbose()
            "status" -> session.controls.status()
            "disconnetti" -> session.controls.disconnectAll()
            "log" -> session.controls.log()
            "test" -> session.controls.test()
            else -> ServerLogger.log("Errore, riprovare!\n", References.LEVEL_SERVER)
        }
    }

    working = true
}