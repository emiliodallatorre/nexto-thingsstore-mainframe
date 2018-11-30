package com.nextocompany.thingsstore.test

import com.nextocompany.thingsstore.References
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.net.Socket

class ServerTest {

    fun test() {
        val input: BufferedReader
        val output: PrintStream
        val socket: Socket

        try {
            // open a socket connection
            socket = Socket("localhost", References.SERVER_PORT)

            input = BufferedReader(InputStreamReader(socket.getInputStream()))
            output = PrintStream(socket.getOutputStream())

            Thread.sleep(10000)

            println("Messaggio Ricevuto : " + input.readLine())

            output.println("Ciao, l\'output va!")
            output.flush()

            output.close()
            input.close()
        } catch (e: Exception) {
            println(e.message)
        }

    }
}