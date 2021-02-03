package simpledi.domain

case class User(id: Option[String], userName: String, email: EmailAddr)

// To be used by actions needing UserRepo capabilities
trait UserRepoEnv {
    val userRepo: UserRepo
}

// Capabilities needed to perform operations on users
trait UserRepo {
    def getUser(userId: String): Result[Option[User]]
    def getAllUsers(limit: Int): Result[List[User]]
    def saveUser(user: User): Result[User]
}