package simpledi.domain.api

import org.scalatest.{ FunSuite, Matchers }
import simpledi.domain._
import simpledi.infrastructure.storage._
import simpledi.utils._

object testDev {
    val loggingDev = new LoggingUtilConsole()
    val randomDev = new RandomUtilImpl()
    val env = new UserRepoEnv { 
        val userRepo = new UserRepoInMemory(List())( 
            new RandomUtilEnv with LoggingUtilEnv {
                val random = randomDev
                val logging = loggingDev
            }
        )
    }
}

class UserApiTest extends FunSuite with Matchers {
    test("save user") {
        implicit val env = testDev.env

        val user = User(None, "Baz", EmailAddr("baz.com"))
        UserApi.saveUser(user)

        env.userRepo.data.size shouldBe 1
        env.userRepo.data.head.userName shouldBe "Baz"
        env.userRepo.data.head.email shouldBe EmailAddr("baz.com")
        env.userRepo.data.head.id shouldBe a [Some[_]]
    }
}