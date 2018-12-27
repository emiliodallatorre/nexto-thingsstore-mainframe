package com.nextocompany.thingsstore

import com.nextocompany.thingsstore.base.*
import com.nextocompany.thingsstore.test.ServerTest
import java.util.*

object ServerSession {
    lateinit var initializer: ServerInitializer

    lateinit var logger: ServerLogger
    lateinit var controls: ServerControls
    lateinit var listener: ConnectionListener
    lateinit var waiter: ConnectionWaiter
    lateinit var tester: ServerTest

    lateinit var scanner: Scanner

    var serverStatus: Int = 0
    var verboseOutput: Boolean = true
}