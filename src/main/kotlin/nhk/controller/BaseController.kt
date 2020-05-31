package nhk.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory

open class BaseController {
    protected val logger: Logger = LoggerFactory.getLogger(this.javaClass)
}