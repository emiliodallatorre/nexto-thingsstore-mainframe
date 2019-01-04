package com.nextocompany.thingsstore.test

import com.nextocompany.thingsstore.handler.mysql.LoginManager

class ServerTest {

    fun test() {
        LoginManager().connect()
    }
}