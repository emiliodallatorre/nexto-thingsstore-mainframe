package com.nextocompany.thingsstore.test

import com.nextocompany.thingsstore.session

class ServerTest {

    fun test() {
        session.login.connect()
        session.login.login("emiliodallatorr12e12@live.com", "blindEye1201")
    }
}