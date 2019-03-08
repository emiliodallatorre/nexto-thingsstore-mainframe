package com.nextocompany.thingsstore

/**
 * Qui sono contenute tutte le referenze costanti che servono a gestire il server.
 * Queste referenze possono essere considerate anche come "impostazioni".
 * I valori costanti qui inseriti regolano il funzionamento dell'intero server.
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
    const val STATUS_ERROR: Int = 1

    const val LEVEL_ERROR: Int = 0
    const val LEVEL_SERVER: Int = 1
    const val LEVEL_WARNING: Int = 2
    const val LEVEL_MESSAGE: Int = 3
    const val LEVEL_LOG: Int = 4
    const val LEVEL_FILE: Int = 5

    const val SERVER_PORT: Int = 2048
    const val SERVER_TEST_PORT: Int = 2049
    const val SERVER_MAXCONNECTIONS: Int = 10
    const val SERVER_REFRESHRATE: Long = 1 * 1000

    const val FILE_LOG: String = "Server.log"

    const val CODE_TEST: Int = 0
    const val CODE_LOGIN: Int = 1
    const val CODE_TEST_STRING: String = "023b1613de718b88701e45fd1035d23d"
    const val CODE_USERDATA: Int = 2

    const val SQL_USERNAME: String = "server"
    const val SQL_PASSWORD: String = "aRKano8d"
    const val SQL_PORT: Int = 3306
    const val SQL_DATABASE: String = "utenti"
    const val SQL_TABLE: String = "login"
    const val SQL_TIMEZONE: String = "UTC"
}