package com.nextocompany.thingsstore

class ServerLogger() {
    fun log(message: String, level: Int) {
        lateinit var ANSI_COLOR: String

        when (level) {
            References.LEVEL_ERROR -> ANSI_COLOR = References.ANSI_RED
            References.LEVEL_SERVER -> ANSI_COLOR = References.ANSI_CYAN
            References.LEVEL_WARNING -> ANSI_COLOR = References.ANSI_YELLOW
            References.LEVEL_MESSAGE -> ANSI_COLOR = References.ANSI_GREEN
            References.LEVEL_LOG -> ANSI_COLOR = References.ANSI_WHITE
        }

        if(verboseOutput) println("$ANSI_COLOR$message" + References.ANSI_RESET)
        else if(level < 3) println("$ANSI_COLOR$message" + References.ANSI_RESET)
    }
}