package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.ServerSession

class ServerControls {

    fun status() {
        when (ServerSession.serverStatus) {
            References.STATUS_RUNNING -> ServerSession.logger.log(
                "Il server sta funzionando normalmente.",
                References.LEVEL_SERVER
            )
            References.STATUS_STOPPED -> ServerSession.logger.log(
                "Il server è stato interrotto dall'amministratore.",
                References.LEVEL_SERVER
            )
        }
    }

    fun verbose() {
        ServerSession.verboseOutput = !ServerSession.verboseOutput

        if (ServerSession.verboseOutput) ServerSession.logger.log("Modalità verbosa attivata.",
            References.LEVEL_SERVER
        )
        else ServerSession.logger.log("Modalità verbosa disattivata.",
            References.LEVEL_SERVER
        )
    }

    fun stop() {
        ServerSession.logger.log("Tentativo di disattivare l\'ascolto di nuove connessioni.",
            References.LEVEL_SERVER
        )

        if (ServerSession.serverStatus == References.STATUS_STOPPED) ServerSession.logger.log(
            "Il server è già inattivo.",
            References.LEVEL_MESSAGE
        )
        else ServerSession.listener.stopListening()
    }

    fun start() {
        ServerSession.logger.log("Tentativo di attivare l\'ascolto di nuove connessioni.",
            References.LEVEL_SERVER
        )

        if (ServerSession.serverStatus == References.STATUS_RUNNING) ServerSession.logger.log(
            "Il server è già attivo.",
            References.LEVEL_MESSAGE
        )
        else ServerSession.listener.startListening()
    }

    fun disconnectAll() {
        ServerSession.logger.log("Avvio disconnessione degli utenti.",
            References.LEVEL_SERVER
        )
        Thread().run {
            ServerSession.listener.executor.shutdown()
            while (!ServerSession.listener.executor.isTerminated) {
                Thread.sleep(References.SERVER_REFRESHRATE)
            }

            ServerSession.logger.log("Tutti gli utenti sono stati disconnessi correttamente.",
                References.LEVEL_WARNING
            )
            ServerSession.logger.log("Payout del server: " + ServerSession.listener.executor,
                References.LEVEL_LOG
            )

            ServerSession.initializer.initListener()
        }
    }

    fun log() {
        ServerSession.logger.log("Inserire testo da loggare nel file: ",
            References.LEVEL_MESSAGE
        )
        ServerSession.logger.log("# USER: " + ServerSession.scanner.nextLine(),
            References.LEVEL_FILE
        )
    }
}