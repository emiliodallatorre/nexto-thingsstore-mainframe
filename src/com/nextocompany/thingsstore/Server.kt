package com.nextocompany.thingsstore

import java.net.ServerSocket
import java.net.Socket
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

const val serverPort: Int = 2048
const val maxConnections: Int = 10

private val serverSocket: ServerSocket = ServerSocket(serverPort)

fun main(args: Array<String>) {
    val executor: ExecutorService = Executors.newFixedThreadPool(maxConnections)

    while (true) {
        val clientSocket: Socket = serverSocket.accept()

        println("\nConnessione in ingresso da: " + clientSocket.inetAddress.hostAddress)
        println("Avvio gestione della connessione!")

        val connectionHandler = ConnectionHandler()
        connectionHandler.clientSocket = clientSocket
        
        executor.execute(connectionHandler)

        println("Gestione della connessione inviata a Thread.")
    }
}

private class ConnectionHandler : Thread() {
    val ANSI_RESET = "\u001B[0m"
    val ANSI_BLACK = "\u001B[30m"
    val ANSI_RED = "\u001B[31m"
    val ANSI_GREEN = "\u001B[32m"
    val ANSI_YELLOW = "\u001B[33m"
    val ANSI_BLUE = "\u001B[34m"
    val ANSI_PURPLE = "\u001B[35m"
    val ANSI_CYAN = "\u001B[36m"
    val ANSI_WHITE = "\u001B[37m"

    lateinit var clientSocket: Socket

    override fun run() {
        println("Avvio della gestione di: " + clientSocket.inetAddress.hostAddress, 0)
        Thread.sleep(20000)
        println("Disconnessione da: " + clientSocket.inetAddress.hostAddress, 1)
        clientSocket.close()
    }

    fun println(message: String, level: Int) {
        lateinit var ANSI_COLOR: String
        val threadId: Long = Thread.currentThread().id


        when(level) {
            0 -> ANSI_COLOR = ANSI_GREEN
            1 -> ANSI_COLOR = ANSI_YELLOW
            2 -> ANSI_COLOR = ANSI_RED
        }

        System.out.println("$ANSI_COLOR[ $threadId ] $message$ANSI_RESET")
    }
}