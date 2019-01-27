package com.nextocompany.thingsstore

import com.nextocompany.thingsstore.base.ServerInitializer

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
                else -> session.logger.log("Errore, riprovare!\n", References.LEVEL_SERVER)
            }

    }
    working = true
}