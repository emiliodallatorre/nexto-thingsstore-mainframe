package com.nextocompany.thingsstore.handler

import com.nextocompany.thingsstore.References
import java.io.DataInputStream
import java.io.DataOutputStream

class ServerFunctions {

    fun validateLogin(input: DataInputStream, output: DataOutputStream) {
        // TODO: Change this way.
        val rawData = input.readUTF()
        val id = rawData.split("§")[0]
        val password = rawData.split("§")[1]

        output.writeBoolean(id == "emiliodallatorre12@live.com" && password == "blindEye1201")
        output.flush()

        if(id == "emiliodallatorre12@live.com" && password == "blindEye1201") ConnectionHandler().ping("Login di $id eseguito correttamente.", References.LEVEL_LOG)
        else ConnectionHandler().ping("La password inserita da $id è sbagliata.", References.LEVEL_WARNING)
    }

    fun test(input: DataInputStream, output: DataOutputStream) {
        if (input.readUTF() == References.CODE_TEST_STRING) {
            ConnectionHandler().ping("Connessione confermata.", References.LEVEL_LOG)

            output.writeByte(References.CODE_TEST)
            output.writeUTF(References.CODE_TEST_STRING)
            output.flush()
        }
    }
}