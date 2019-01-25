package com.nextocompany.thingsstore.handler.mysql

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.session
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

/** Questa classe permette di controllare la validità delle credenziali.
 * Il controllo viene fatto a fronte di un database MySQL su localhost.
 * In questa classe si possono implementare le funzioni di sicurezza.
 */

class LoginManager {
    private lateinit var connection: Connection

    fun connect(): Boolean {
        return try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:" + References.SQL_PORT + "/" + References.SQL_DATABASE + "?serverTimezone=" + References.SQL_TIMEZONE,
                References.SQL_USERNAME,
                References.SQL_PASSWORD
            )
            true
        } catch (e: SQLException) {
            session.logger.log("SQLException: " + e.message, References.LEVEL_ERROR)
            session.logger.log("SQLState: " + e.sqlState, References.LEVEL_ERROR)
            session.logger.log("VendorError: " + e.errorCode, References.LEVEL_ERROR)
            false
        }
    }

    fun login(id: String, password: String): Boolean {
        val resultSet: ResultSet = connection.createStatement().executeQuery("SELECT * FROM " + References.SQL_TABLE + " WHERE (username='$id' || email='$id') && password='$password'")
        lateinit var correctPassword: String

        while (resultSet.next()) {
            correctPassword = resultSet.getString("password")
        }

        // TODO: Questo funzionerà quando Kotlin rilascerà questa funzione al pubblico.
        // if(::correctPassword.isInitialized) return false
        try {
            @Suppress("UNUSED_EXPRESSION")
            correctPassword
        } catch (e: kotlin.UninitializedPropertyAccessException) {
            return false
        }
        // Questo workaround permette di controllare se la variabile è stata inizializzata.

        return password == correctPassword
    }
}