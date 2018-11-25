package com.nextocompany.thingsstore

import java.util.*

val session: ServerSession = ServerSession

fun main(args: Array<String>) {
    session.initializer = ServerInitializer()

    session.listener.start()

    while (true) {
        when (session.scanner.nextLine()) {
            "stop" -> session.controls.stop()
            "start" -> session.controls.start()
            "verbose" -> session.controls.verbose()
            "status" -> session.controls.status()
            else -> session.logger.log("Errore, riprovare!\n", References.LEVEL_SERVER)
        }
    }
}