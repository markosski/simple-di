package simpledi.infrastructure

import simpledi.domain.EmailSender
import simpledi.domain._
import simpledi.utils._
import simpledi._

// Concrete implementation of EmailSender
class EmailSenderNoOp()(env: LoggingUtilEnv with AppConfigEnv) extends EmailSender {
    def send(email: EmailAddr, message: EmailMessage): Result[EmailAddr] = {
        if (!email.email.contains("@")) {
            env.logging.info(s"DID NOT send email to $email")
            Left(Error(s"Email address: $email, is not valid"))
        } else {
            env.logging.info(s"Sent email to $email with title: '${message.subject}' and message: '${message.body.take(10)}...' from ${env.config.senderEmail}")
            Right(email)
        }
    }
}