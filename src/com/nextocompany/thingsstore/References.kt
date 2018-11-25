package com.nextocompany.thingsstore

/**
 * Qui sono contenute tutte le referenze costanti che servono a gestire il server.
 * Queste referenze possono essere considerate anche come "impostazioni".
 * I const valori qui inseriti regolano il funzionamento dell'intero server.
 */

object References {
    const val ANSI_RESET: String = "\u001B[0m"
    const val ANSI_BLACK: String = "\u001B[30m"
    const val ANSI_RED: String = "\u001B[31m"
    const val ANSI_GREEN: String = "\u001B[32m"
    const val ANSI_YELLOW: String = "\u001B[33m"
    const val ANSI_BLUE: String = "\u001B[34m"
    const val ANSI_PURPLE: String = "\u001B[35m"
    const val ANSI_CYAN: String = "\u001B[36m"
    const val ANSI_WHITE: String = "\u001B[37m"

    const val STATUS_RUNNING: Int = 0
    const val STATUS_STOPPED: Int = 1

    const val LEVEL_ERROR: Int = 0
    const val LEVEL_SERVER: Int = 1
    const val LEVEL_WARNING: Int = 2
    const val LEVEL_MESSAGE: Int = 3
    const val LEVEL_LOG: Int = 4

    const val SERVER_PORT: Int = 2048
    const val SERVER_MAXCONNECTIONS: Int = 10
    const val SERVER_REFRESHRATE: Long = 10 * 1000

    const val FILE_LOG: String = "Server.log"
}