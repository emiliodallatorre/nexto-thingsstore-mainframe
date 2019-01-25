import com.nextocompany.thingsstore.Server
import org.junit.jupiter.api.Test

internal class ServerKtTest {

    @Test
    fun main() {
        val main = Server()
        main.test = true
        main.main()
        assert(main.working)
    }
}