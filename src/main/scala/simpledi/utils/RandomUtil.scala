package simpledi.utils

import java.util.UUID
import _root_.java.util.Random

trait RandomUtilEnv {
    val random: RandomUtil
}

trait RandomUtil {
    def getUUID: String
}

class RandomUtilImpl extends RandomUtil {
    def getUUID: String = {
        UUID.randomUUID().toString()
    }
}