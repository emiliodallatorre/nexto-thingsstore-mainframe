package com.nextocompany.thingsstore.handler.mysql

import com.nextocompany.thingsstore.References
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class LoginManager {
    private lateinit var connection: Connection

    fun connect() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/utenti",
                References.SQL_USERNAME,
                References.SQL_PASSWORD
            )
        } catch (e: SQLException) {
            // handle any errors
            System.out.println("SQLException: " + e.message)
            System.out.println("SQLState: " + e.sqlState)
            System.out.println("VendorError: " + e.errorCode)
        }
    }
}