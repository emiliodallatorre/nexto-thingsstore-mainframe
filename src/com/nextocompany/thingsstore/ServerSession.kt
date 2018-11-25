package com.nextocompany.thingsstore

import java.util.*

object ServerSession {
    lateinit var logger: ServerLogger
    lateinit var controls: ServerControls
    lateinit var scanner: Scanner
    lateinit var listener: Thread

    var serverStatus: Int = 0
    var verboseOutput: Boolean = false
}