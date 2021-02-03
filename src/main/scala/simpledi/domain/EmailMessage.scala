package simpledi.domain

case class EmailMessage(subject: String, body: String)

trait EmailSenderEnv {
    val emailSender: EmailSender
}

trait EmailSender {
    def send(email: EmailAddr, message: EmailMessage): Result[EmailAddr]
}