package com.nextocompany.thingsstore

class ServerControls {
    fun status() {
        when () {
            References.STATUS_RUNNING -> log("Il server sta funzionando normalmente.", References.LEVEL_SERVER)
            References.STATUS_STOPPED -> log("Il server è stato interrotto dall'amministratore.", References.LEVEL_SERVER)
        }
    }
    fun verbose() {
        verboseOutput = !verboseOutput
        if (verboseOutput) log("Modalità verbosa attivata.\n", References.LEVEL_SERVER)
        else log("Modalità verbosa disattivata.\n", References.LEVEL_SERVER)
    }

    fun disconnetti() {
        executor.shutdown()
        log("Tutte le connessioni sono state disconnesse.\n", References.LEVEL_SERVER)
    }

    fun stop() {
        serverStatus = References.STATUS_STOPPED
        log("Il server non accetta più connessioni in ingresso.\n", References.LEVEL_SERVER)
    }
}