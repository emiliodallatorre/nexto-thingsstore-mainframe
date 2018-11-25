package com.nextocompany.thingsstore

import java.net.Socket

class ConnectionHandler : Thread() {
    lateinit var clientSocket: Socket

    override fun run() {
        // Qui si procede alle azioni necessarie a gestire una connessione entrante.
        println("Avvio della gestione di: " + clientSocket.inetAddress.hostAddress, References.LEVEL_LOG)
        Thread.sleep(20000)
        clientSocket.close()
        println("Disconnessione da: " + clientSocket.inetAddress.hostAddress, References.LEVEL_LOG)
    }

    fun println(message: String, level: Int) {
        // Qui viene aggiunto il prefisso del thread all'output del log.
        session.logger.log("[ " + String.format("%03d", Thread.currentThread().id) + " ] $message", level)
    }
}