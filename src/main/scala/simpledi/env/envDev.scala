package simpledi

import simpledi._
import simpledi.domain._
import simpledi.infrastructure.storage._
import simpledi.infrastructure._
import simpledi.utils._

// Constructing all capabilities for Dev environment
object envDev {
    private val loggingDev = new LoggingUtilConsole()
    private val configDev = new AppConfig(EmailAddr("admin@mymail.com"))
    private val randomDev = new RandomUtilImpl()

    private val userRepoInitData = List(
        User(Some("1"), "Foo", EmailAddr("foo@mymail.com")),
        User(Some("2"), "Bar", EmailAddr("bar@mymail.com"))
        )

    implicit val env = new AppConfigEnv with RandomUtilEnv with UserRepoEnv with LoggingUtilEnv with EmailSenderEnv {
        val random = randomDev
        val config = configDev
        val logging = loggingDev

        val userRepo = new UserRepoInMemory(userRepoInitData)( 
            new RandomUtilEnv with LoggingUtilEnv {
                val random = randomDev
                val logging = loggingDev
            }
        )

        val emailSender = new EmailSenderImpl()(
            new LoggingUtilEnv with AppConfigEnv {
                val logging = loggingDev
                val config = configDev
            }
        )
    }
}