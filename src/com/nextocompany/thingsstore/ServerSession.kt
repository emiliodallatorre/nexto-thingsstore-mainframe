package com.nextocompany.thingsstore

object ServerSession {
    lateinit var logger: ServerLogger
    lateinit var controls: ServerControls
    var serverStatus: Int = 0
    var verboseOutput: Boolean = false
}