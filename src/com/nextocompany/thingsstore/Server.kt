package com.nextocompany.thingsstore

import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket

const val serverPort: Int = 488

private val serverSocket: ServerSocket = ServerSocket(serverPort)

fun main(args: Array<String>) {
    while (true) {
        val clientSocket: Socket = serverSocket.accept()
        print("Nuova connessione socket!")
        Thread {
            val clientAddress: InetAddress = clientSocket.inetAddress
            System.out.println("Connessione in ingresso da: " + clientAddress.hostAddress + "]")
            clientSocket.close()
        }
    }
}