package simpledi

import simpledi.domain._

object Hello extends App {
  val env = EnvDev.env

  val result = for {
    _        <- env.userRepo.saveUser(User(None, "Baz", EmailAddr("baz.com")))
    _        <- env.userRepo.saveUser(User(None, "Qux", EmailAddr("qux.com")))
    allUsers <- env.userRepo.getAllUsers(10)
    _        <- Right(env.logging.info(s"Found users: ${allUsers.size}"))
    message  = EmailMessage("Covid 19", "Please, put your mask on!")
    sentResult <- (allUsers.map { user =>
        env.emailSender.send(user.email, message)
      }).partition(_.isLeft) match {
        case (Nil, xs) => Right(xs.map(_.right.get))
        case (err, xs) => Left(Error(err.map(_.left.get.msg).mkString("; ")))
      }
  } yield sentResult

  result match {
    case Left(Error(err)) => env.logging.error(err)
    case Right(succ) => env.logging.info(s"SUCCESS: ${succ}, all done")
  }
}
