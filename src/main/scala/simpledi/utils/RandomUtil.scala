package simpledi.utils

import java.util.UUID
import _root_.java.util.Random

trait RandomUtilEnv {
    val random: RandomUtil
}

// Capabilities needed to use random values
trait RandomUtil {
    def getUUID: String
}

// Concrete implementation of RandomUtil
class RandomUtilImpl extends RandomUtil {
    def getUUID: String = {
        UUID.randomUUID().toString()
    }
}