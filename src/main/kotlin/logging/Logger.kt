package logging

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Logger{
    companion object {
        fun v(message: String, className: String){
            logging(LogLevel.VERBOSE, "[$className] $message")
        }

        fun d(message: String, className: String){
            logging(LogLevel.DEBUG,"[$className] $message")
        }

        fun i(message: String, className: String){
            logging(LogLevel.INFO, "[$className] $message")
        }

        fun w(message: String, className: String){
            logging(LogLevel.WARN, "[$className] $message")
        }

        fun e(message: String){
            logging(LogLevel.ERROR, message)
        }

        fun e(e: Exception){
            logging(LogLevel.ERROR, e.message!!)
        }

        fun f(message: String){
            logging(LogLevel.FATAL, message)
        }
        fun f(e: Exception){
            logging(LogLevel.FATAL, e.message!!)
        }

        private fun logging(level: LogLevel, message: String){
            val time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            println("$time [${level.name.substring(0,1).toUpperCase()}]: $message")
        }
    }

}
