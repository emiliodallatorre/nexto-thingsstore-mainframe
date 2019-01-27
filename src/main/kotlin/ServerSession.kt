package com.nextocompany.thingsstore

import com.nextocompany.thingsstore.base.*
import com.nextocompany.thingsstore.handler.mysql.LoginManager

object ServerSession {
    lateinit var initializer: ServerInitializer

    lateinit var logger: ServerLogger
    lateinit var controls: ServerControls
    lateinit var listener: ConnectionListener
    lateinit var waiter: ConnectionWaiter
    lateinit var login: LoginManager

    var serverStatus: Int = 0
    var verboseOutput: Boolean = true
}