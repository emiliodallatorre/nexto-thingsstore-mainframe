package com.nextocompany.thingsstore.handler

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session
import java.io.DataInputStream
import java.io.DataOutputStream

/**
 * In questa classe sono gestite le interazioni con tutti i client connessi.
 * Da qui partono tutte le risposte ai client e si svolge il grosso del lavoro.
 */

class ServerFunctions {
    fun validateLogin(input: DataInputStream, output: DataOutputStream) {
        // TODO: Change this way.
        val rawData = input.readUTF()
        val id = rawData.split("§")[0]
        val password = rawData.split("§")[1]

        if(session.login.login(id, password)) {
            output.writeBoolean(true)
            output.flush()
            ConnectionHandler().ping("Login di $id eseguito correttamente.", References.LEVEL_LOG)
        }
        else {
            output.writeBoolean(false)
            output.flush()
            ConnectionHandler().ping("La password inserita da $id è sbagliata.", References.LEVEL_WARNING)
        }
    }

    fun test(input: DataInputStream, output: DataOutputStream) {
        if (input.readUTF() == References.CODE_TEST_STRING) {
            ConnectionHandler().ping("Connessione confermata.", References.LEVEL_LOG)

            output.writeByte(References.CODE_TEST)
            output.writeUTF(References.CODE_TEST_STRING)
            output.flush()
        }
    }

    fun getUserData(output: DataOutputStream, id: String) {
        ConnectionHandler().ping("Inizio trasmissione dati di $id.", References.LEVEL_LOG)
        output.writeByte(References.CODE_USERDATA)
        output.writeUTF(session.login.userData(id))
        output.flush()
    }
}