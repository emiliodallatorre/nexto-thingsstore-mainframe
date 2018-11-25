package com.nextocompany.thingsstore

class ServerControls {

    fun status() {
        when (session.serverStatus) {
            References.STATUS_RUNNING -> session.logger.log(
                "Il server sta funzionando normalmente.",
                References.LEVEL_SERVER
            )
            References.STATUS_STOPPED -> session.logger.log(
                "Il server è stato interrotto dall'amministratore.",
                References.LEVEL_SERVER
            )
        }
    }

    fun verbose() {
        session.verboseOutput = !session.verboseOutput

        if (session.verboseOutput) session.logger.log("Modalità verbosa attivata.", References.LEVEL_SERVER)
        else session.logger.log("Modalità verbosa disattivata.", References.LEVEL_SERVER)
    }

    fun stop() {
        session.logger.log("Tentativo di disattivare l\'ascolto di nuove connessioni.", References.LEVEL_SERVER)

        if (session.serverStatus == References.STATUS_STOPPED) session.logger.log("Il server è già inattivo.", References.LEVEL_MESSAGE)
        else session.serverStatus = References.STATUS_STOPPED
    }

    fun start() {
        session.logger.log("Tentativo di attivare l\'ascolto di nuove connessioni.", References.LEVEL_SERVER)

        if (session.serverStatus == References.STATUS_RUNNING) session.logger.log("Il server è già attivo.", References.LEVEL_MESSAGE)
        else session.serverStatus = References.STATUS_RUNNING
    }
}