package simpledi.infrastructure.storage

import simpledi.AppConfig
import simpledi.domain._
import simpledi.utils._
import simpledi._

// Concrete implementation of UserRepo
class UserRepoInMemory(var data: List[User])(val env: RandomUtilEnv with LoggingUtilEnv) extends UserRepo {
    def getUser(userId: String): Result[Option[User]] = {
        env.logging.info("Getting user from DB")
        val result = data.filter(x => x.id.fold(false)(id => id == userId))
            .headOption
        Right(result)
    }

    def saveUser(user: User): Result[User] = {
        val newUser = user.copy(id = Some(env.random.getUUID))
        env.logging.info(s"Saving to DB new user record: ${newUser}")
        data = data :+ newUser
        Right(newUser)
    }

    def getAllUsers(limit: Int): Result[List[User]] = {
        env.logging.info("Getting users from DB")
        Right(data.take(limit))
    }
}