package simpledi.utils

trait LoggingUtilEnv {
    val logging: LoggingUtil
}

// Capabilities needed to perform logging
trait LoggingUtil {
    def debug(msg: String): Unit
    def info(msg: String): Unit
    def warn(msg: String): Unit
    def error(msg: String): Unit
}

// Concrete implementation of LoggingUtil
class LoggingUtilConsole extends LoggingUtil {
    def debug(msg: String) = println(s"DEBUG: $msg")
    def info(msg: String) = println(s"INFO: $msg")
    def warn(msg: String) = println(s"WARN: $msg")
    def error(msg: String) = println(s"ERROR: $msg")
}