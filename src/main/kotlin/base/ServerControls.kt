package com.nextocompany.thingsstore.base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session

/**
 * Qui vengono dichiarati tutti i controlli del server.
 * Di genere, non accettano argomenti.
 * Ciascuno di questi "risponde" all'utente stampando a schermo qualcosa.
 */

class ServerControls {

    companion object {
        fun status() {
            when (session.serverStatus) {
                References.STATUS_RUNNING -> ServerLogger.log(
                    "Il server sta funzionando normalmente.",
                    References.LEVEL_SERVER
                )
                References.STATUS_STOPPED -> ServerLogger.log(
                    "Il server è stato interrotto dall'amministratore.",
                    References.LEVEL_SERVER
                )
            }
        }

        fun verbose() {
            session.verboseOutput = !session.verboseOutput

            if (session.verboseOutput) ServerLogger.log(
                "Modalità verbosa attivata.",
                References.LEVEL_SERVER
            )
            else ServerLogger.log(
                "Modalità verbosa disattivata.",
                References.LEVEL_SERVER
            )
        }

        fun stop() {
            ServerLogger.log(
                "Tentativo di disattivare l\'ascolto di nuove connessioni.",
                References.LEVEL_SERVER
            )

            if (session.serverStatus == References.STATUS_STOPPED) ServerLogger.log(
                "Il server è già inattivo.",
                References.LEVEL_MESSAGE
            )
            else session.listener.stopListening()
        }

        fun start() {
            ServerLogger.log(
                "Tentativo di attivare l\'ascolto di nuove connessioni.",
                References.LEVEL_SERVER
            )

            if (session.serverStatus == References.STATUS_RUNNING) ServerLogger.log(
                "Il server è già attivo.",
                References.LEVEL_MESSAGE
            )
            else session.listener.startListening()
        }

        fun disconnectAll() {
            ServerLogger.log(
                "Avvio disconnessione degli utenti.",
                References.LEVEL_SERVER
            )
            Thread().run {
                session.listener.executor.shutdown()
                while (!session.listener.executor.isTerminated) {
                    Thread.sleep(References.SERVER_REFRESHRATE)
                }

                ServerLogger.log(
                    "Tutti gli utenti sono stati disconnessi correttamente.",
                    References.LEVEL_WARNING
                )
                ServerLogger.log(
                    "Payout del server: " + session.listener.executor,
                    References.LEVEL_LOG
                )

                session.initializer.initListener()
            }
        }

        fun log() {
            ServerLogger.log(
                "Inserire testo da loggare nel file: ",
                References.LEVEL_MESSAGE
            )
            ServerLogger.log(
                "# USER: " + readLine()!!,
                References.LEVEL_FILE
            )
        }

        fun test() {
            // TODO: Implementare.
            ServerLogger.log("Questa funzione non è ancora disponibile.", References.LEVEL_SERVER)
            // ServerLogger.log("Avvio dei test del server.", References.LEVEL_SERVER)
        }
    }
}