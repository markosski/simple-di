package simpledi.domain

case class EmailAddr(email: String)
case class User(id: Option[String], userName: String, email: EmailAddr)

trait UserRepoEnv {
    val userRepo: UserRepo
}

trait UserRepo {
    def getUser(userId: String): Result[Option[User]]
    def getAllUsers(limit: Int): Result[List[User]]
    def saveUser(user: User): Result[Unit]
}