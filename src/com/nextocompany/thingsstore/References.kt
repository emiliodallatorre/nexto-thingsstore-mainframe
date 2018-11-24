package com.nextocompany.thingsstore

object References {
    val ANSI_RESET: String = "\u001B[0m"
    val ANSI_BLACK: String = "\u001B[30m"
    val ANSI_RED: String = "\u001B[31m"
    val ANSI_GREEN: String = "\u001B[32m"
    val ANSI_YELLOW: String = "\u001B[33m"
    val ANSI_BLUE: String = "\u001B[34m"
    val ANSI_PURPLE: String = "\u001B[35m"
    val ANSI_CYAN: String = "\u001B[36m"
    val ANSI_WHITE: String = "\u001B[37m"

    val STATUS_RUNNING: Int = 0
    val STATUS_STOPPED: Int = 1

    val LEVEL_ERROR: Int = 0
    val LEVEL_SERVER: Int = 1
    val LEVEL_WARNING: Int = 2
    val LEVEL_MESSAGE: Int = 3
    val LEVEL_LOG: Int = 4

    const val SERVER_PORT: Int = 2048
    const val SERVER_MAXCONNECTIONS: Int = 10
}