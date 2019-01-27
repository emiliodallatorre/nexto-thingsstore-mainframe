package base

import com.nextocompany.thingsstore.References
import com.nextocompany.thingsstore.base.ConnectionListener
import com.nextocompany.thingsstore.base.ConnectionWaiter
import com.nextocompany.thingsstore.base.ServerInitializer
import com.nextocompany.thingsstore.base.ServerLogger
import com.nextocompany.thingsstore.handler.mysql.LoginManager
import com.nextocompany.thingsstore.session
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class ServerInitializerTest {
    private val serverInitializer: ServerInitializer = ServerInitializer()

    @Test
    fun initLogger() {
        serverInitializer.initLogger()
        assert(session.logger is ServerLogger)
    }

    @Test
    fun initListener() {
        serverInitializer.initListener()
        assert(session.listener is ConnectionListener)
    }

    @Test
    fun initWaiter() {
        serverInitializer.initWaiter(References.SERVER_TEST_PORT)
        assert(session.waiter is ConnectionWaiter)
    }

    // Questo test è disabilitato poiché in Travis non c'è connessione al server MySQL locale.
    @Disabled
    @Test
    fun initLogin() {
        serverInitializer.initLogin()
        assert(session.login is LoginManager)
    }

    @Test
    fun initAll() {
        serverInitializer.initAll()
        assert(session.logger is ServerLogger)
        assert(session.listener is ConnectionListener)
        assert(session.waiter is ConnectionWaiter)
        assert(session.login is LoginManager)
    }
}