package simpledi

import simpledi._
import simpledi.domain._
import simpledi.infrastructure.storage._
import simpledi.infrastructure._
import simpledi.utils._

object EnvDev {
    private val loggingDev = new LoggingUtilConsole()
    private val configDev = new AppConfig()
    private val randomDev = new RandomUtilImpl()

    private val userRepoInitData = List(
        User(Some("1"), "Foo", EmailAddr("foo@mymail.com")),
        User(Some("2"), "Bar", EmailAddr("bar@mymail.com"))
        )

    val env = new AppConfigEnv with RandomUtilEnv with UserRepoEnv with LoggingUtilEnv with EmailSenderEnv {
        val random = randomDev
        val config = configDev
        val logging = loggingDev

        val userRepo = new UserRepoInMemory(userRepoInitData)( 
            new RandomUtilEnv with AppConfigEnv with LoggingUtilEnv {
                val random = randomDev
                val config = configDev
                val logging = loggingDev
            }
        )

        val emailSender = new EmailSenderImpl()(
            new LoggingUtilEnv {
                val logging = loggingDev
            }
        )
    }
}