package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session

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

        if (session.verboseOutput) session.logger.log("Modalità verbosa attivata.",
            References.LEVEL_SERVER
        )
        else session.logger.log("Modalità verbosa disattivata.",
            References.LEVEL_SERVER
        )
    }

    fun stop() {
        session.logger.log("Tentativo di disattivare l\'ascolto di nuove connessioni.",
            References.LEVEL_SERVER
        )

        if (session.serverStatus == References.STATUS_STOPPED) session.logger.log(
            "Il server è già inattivo.",
            References.LEVEL_MESSAGE
        )
        else session.listener.stopListening()
    }

    fun start() {
        session.logger.log("Tentativo di attivare l\'ascolto di nuove connessioni.",
            References.LEVEL_SERVER
        )

        if (session.serverStatus == References.STATUS_RUNNING) session.logger.log(
            "Il server è già attivo.",
            References.LEVEL_MESSAGE
        )
        else session.listener.startListening()
    }

    fun disconnectAll() {
        session.logger.log("Avvio disconnessione degli utenti.",
            References.LEVEL_SERVER
        )
        Thread().run {
            session.listener.executor.shutdown()
            while (!session.listener.executor.isTerminated) {
                Thread.sleep(References.SERVER_REFRESHRATE)
            }

            session.logger.log("Tutti gli utenti sono stati disconnessi correttamente.",
                References.LEVEL_WARNING
            )
            session.logger.log("Payout del server: " + session.listener.executor,
                References.LEVEL_LOG
            )

            session.initializer.initListener()
        }
    }

    fun log() {
        session.logger.log("Inserire testo da loggare nel file: ",
            References.LEVEL_MESSAGE
        )
        session.logger.log("# USER: " + session.scanner.nextLine(),
            References.LEVEL_FILE
        )
    }

    fun test() {
        session.logger.log("Avvio dei test del server.", References.LEVEL_SERVER)
        session.tester.test()
    }
}