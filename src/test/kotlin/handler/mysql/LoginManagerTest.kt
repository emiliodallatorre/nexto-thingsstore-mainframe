package handler.mysql

import com.nextocompany.thingsstore.handler.mysql.LoginManager
import org.junit.jupiter.api.Test

internal class LoginManagerTest {
    private val loginManager: LoginManager = LoginManager()

    // Questi test sono disabilitati poiché in Travis non c'è connessione al server MySQL locale.

    @Test
    fun connect() {
        assert(loginManager.connect())
    }

    @Test
    fun login() {
        assert(loginManager.connect())
        assert(loginManager.login("emiliodallatorre12@live.com", "blindEye1201"))
    }
}