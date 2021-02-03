package simpledi.infrastructure

import simpledi.domain.EmailSender
import simpledi.domain._
import simpledi.utils._

class EmailSenderImpl[E <: LoggingUtilEnv]()(env: E) extends EmailSender {
    def send(email: EmailAddr, message: EmailMessage): Result[EmailAddr] = {
        if (!email.email.contains("@")) {
            env.logging.info(s"DID NOT send email to $email")
            Left(Error(s"Email address: $email, is not valid"))
        } else {
            env.logging.info(s"Sent email to $email with title: ${message.subject}")
            Right(email)
        }
    }
}