package com.nextocompany.thingsstore

import java.net.Socket


internal class ConnectionHandler : Thread() {
    lateinit var clientSocket: Socket

    override fun run() {
        println("Avvio della gestione di: " + clientSocket.inetAddress.hostAddress, References.LEVEL_LOG)
        Thread.sleep(20000)
        println("Disconnessione da: " + clientSocket.inetAddress.hostAddress, References.LEVEL_LOG)
        clientSocket.close()
    }

    fun println(message: String, level: Int) {
        com.nextocompany.thingsstore.println(
            "[ " + String.format("%03d", Thread.currentThread().id) + " ] $message",
            level
        )
    }
}