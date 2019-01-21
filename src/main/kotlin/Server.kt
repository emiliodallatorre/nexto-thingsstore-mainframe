package com.nextocompany.thingsstore

import com.nextocompany.thingsstore.base.ServerInitializer
import java.util.*

val session: ServerSession = ServerSession

fun main(args: Array<String>) {
    session.initializer = ServerInitializer()

    session.initializer.initAll()

    session.listener.startListening()

    session.scanner = Scanner(System.`in`)

    while (true) {
        when (session.scanner.nextLine()) {
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
}