package simpledi.domain

case class EmailAddr(email: String)
case class EmailMessage(subject: String, body: String)

// To be used by actions needing EmailSender capabilities
trait EmailSenderEnv {
    val emailSender: EmailSender
}

// Capabilities needed to perform operations on emails
trait EmailSender {
    def send(email: EmailAddr, message: EmailMessage): Result[EmailAddr]
}