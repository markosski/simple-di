package simpledi.domain.api

import simpledi.domain._
import simpledi.utils._

object UserApi {
    def saveUser[E <: UserRepoEnv](user: User)(implicit env: E): Result[User] = {
        env.userRepo.saveUser(user)
    }

    def getUserBatch[E <: UserRepoEnv with LoggingUtilEnv]()(implicit env: E) = {
        val batchSize = 10
        env.logging.info(s"Loading up to $batchSize user records")
        env.userRepo.getAllUsers(batchSize)
    }
}

object EmailApi {
    def sendMessageToUsers[E <: EmailSenderEnv](users: List[User], message: EmailMessage)(implicit env: E): Result[List[EmailAddr]] = {
        for {
            sentResult <- (users.map { user =>
                env.emailSender.send(user.email, message)
            }).partition(_.isLeft) match {
                case (Nil, xs) => Right(xs.collect{ case Right(email) => email })
                case (errs, _) => Left(Error(errs.collect { case Left(err) => err.msg }.mkString("; ")))
            }
        } yield sentResult
    }
}