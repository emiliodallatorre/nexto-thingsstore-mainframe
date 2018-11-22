package com.nextocompany.thingsstore

import java.net.Socket

private val clientSocket: Socket = Socket("Pi-3.local", 2048)

fun main(args: Array<String>) {
    clientSocket.getOutputStream().write(2)
}