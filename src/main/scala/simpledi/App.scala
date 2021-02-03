package simpledi

import simpledi.domain._
import simpledi.domain.api._

object SimpleApp extends App {
  import envDev.env

  val result = for {
    _        <- UserApi.saveUser(User(None, "Baz", EmailAddr("baz.com")))
    _        <- UserApi.saveUser(User(None, "Qux", EmailAddr("qux@mymail.com")))
    allUsers <- UserApi.getUserBatch()
    message  = EmailMessage("Covid 19", "Please put your mask on!")
    sentResult <- EmailApi.sendMessageToUsers(allUsers, message)
  } yield sentResult

  result match {
    case Left(Error(err)) => env.logging.error(err)
    case Right(succ) => env.logging.info(s"SUCCESS: ${succ}, all done")
  }
}
