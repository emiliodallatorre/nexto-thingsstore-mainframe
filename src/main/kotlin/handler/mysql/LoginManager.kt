package com.nextocompany.thingsstore.handler.mysql

import com.nextocompany.thingsstore.References
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

class LoginManager {
    private lateinit var connection: Connection

    fun connect() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:" + References.SQL_PORT + "/" + References.SQL_DATABASE + "?serverTimezone=" + References.SQL_TIMEZONE,
                References.SQL_USERNAME,
                References.SQL_PASSWORD
            )

        } catch (e: SQLException) {
            System.out.println("SQLException: " + e.message)
            System.out.println("SQLState: " + e.sqlState)
            System.out.println("VendorError: " + e.errorCode)
        }
    }

    fun login(id: String, password: String): Boolean {
        val resultSet: ResultSet = connection.createStatement().executeQuery("SELECT * FROM " + References.SQL_TABLE + " WHERE (username='$id' || email='$id') && password='$password'")
        lateinit var correctPassword: String

        while (resultSet.next()) {
            correctPassword = resultSet.getString("password")
        }

        // TODO: This needs to be implemented by updating Kotlin.
        // if(::correctPassword.isInitialized) return false

        return password == correctPassword
    }
}