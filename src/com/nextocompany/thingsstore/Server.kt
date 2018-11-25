package com.nextocompany.thingsstore

import java.io.BufferedWriter
import java.io.FileWriter
import java.util.*
import java.util.concurrent.Executors

val session: ServerSession = ServerSession

fun main(args: Array<String>) {
    init()

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

fun init() {
    session.logger = ServerLogger()
    session.logger.bufferedWriter = BufferedWriter(FileWriter(References.FILE_LOG, true))

    val listenerInitializer = ConnectionListener()
    listenerInitializer.executor = Executors.newFixedThreadPool(References.SERVER_MAXCONNECTIONS)
    session.listener = listenerInitializer

    session.controls = ServerControls()

    session.scanner = Scanner(System.`in`)

    session.logger.log("Server inizializzato correttamente.", References.LEVEL_SERVER)
}