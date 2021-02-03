package simpledi.infrastructure.storage

import simpledi.AppConfig
import simpledi.domain._
import simpledi.utils._
import simpledi._

class UserRepoInMemory[E <: RandomUtilEnv with AppConfigEnv with LoggingUtilEnv](var data: List[User])(val env: E) extends UserRepo {
    def getUser(userId: String): Result[Option[User]] = {
        env.logging.info("Getting user by ID")
        val result = data.filter(x => x.id.fold(false)(id => id == userId))
            .headOption
        Right(result)
    }
    def saveUser(user: User): Result[Unit] = {
        val newUser = user.copy(id = Some(env.random.getUUID))
        env.logging.info(s"Saving new user record: ${newUser}")
        data = data :+ newUser
        Right(())
    }
    def getAllUsers(limit: Int): Result[List[User]] = {
        env.logging.info("Getting all users")
        Right(data)
    }
}