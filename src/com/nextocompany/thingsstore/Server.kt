package com.nextocompany.thingsstore

import java.net.ServerSocket
import java.net.Socket

const val serverPort: Int = 2048

private val serverSocket: ServerSocket = ServerSocket(serverPort)

fun main(args: Array<String>) {
    while (true) {
        val clientSocket: Socket = serverSocket.accept()

        println("\nConnessione in ingresso da: " + clientSocket.inetAddress.hostAddress)
        println("Avvio gestione della connessione!")

        Thread {
            // Do something with input.
        }
    }
}