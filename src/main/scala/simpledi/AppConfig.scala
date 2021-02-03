package simpledi

import simpledi.domain.EmailAddr

trait AppConfigEnv {
    val config: AppConfig
}

case class AppConfig(senderEmail: EmailAddr)